package on.logistics.orderservice.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.orderservice.infrastructure.clients.ai.dtos.GenerateShippingDeadlineRequestDto;
import on.logistics.orderservice.infrastructure.clients.ai.feign.dtos.GenerateShippingDeadlineResponse;
import on.logistics.orderservice.infrastructure.clients.company.feign.dtos.GetCompanyResponse;
import on.logistics.orderservice.infrastructure.clients.delivery.dtos.DeliveryRequestDto;
import on.logistics.orderservice.infrastructure.clients.exception.ExternalApiException;
import on.logistics.orderservice.infrastructure.clients.product.dtos.DecreaseProductStockRequestDto;
import on.logistics.orderservice.infrastructure.clients.product.dtos.RollbackDecreaseProductStockRequestDto;
import on.logistics.orderservice.infrastructure.clients.product.feign.dtos.GetProductResponse;
import on.logistics.orderservice.application.service.dtos.create.CreateOrderRequestDto;
import on.logistics.orderservice.application.service.dtos.create.CreateOrderRequestDto.OrdersByVendor;
import on.logistics.orderservice.application.service.dtos.create.CreateOrderRequestDto.OrdersByVendor.OrderedProduct;
import on.logistics.orderservice.application.service.dtos.create.CreateOrderResponseDto;
import on.logistics.orderservice.domain.entity.Order;
import on.logistics.orderservice.domain.entity.OrderProduct;
import on.logistics.orderservice.domain.entity.Orderer;
import on.logistics.orderservice.domain.entity.Vendor;
import on.logistics.orderservice.domain.entity.VendorOrder;
import on.logistics.orderservice.domain.entity.dtos.CreateOrderDto;
import on.logistics.orderservice.domain.entity.dtos.CreateOrderProductDto;
import on.logistics.orderservice.domain.entity.dtos.CreateOrdererDto;
import on.logistics.orderservice.domain.entity.dtos.CreateVendorDto;
import on.logistics.orderservice.domain.entity.dtos.CreateVendorOrderDto;
import on.logistics.orderservice.domain.repository.OrderRepository;
import on.logistics.orderservice.exception.OrderException.OutOfStockProductOrderException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  private final ProductService productService;
  private final CompanyService companyService;
  private final DeliveryService deliveryService;
  private final AIService aiService;

  @Transactional
  public CreateOrderResponseDto createOrder(final CreateOrderRequestDto requestDto) {
    log.info("주문 생성 요청");

    Order savedOrder = saveOrder(requestDto);

    return CreateOrderResponseDto.from(savedOrder);
  }

  private Order saveOrder(final CreateOrderRequestDto requestDto) {
    log.info("주문 생성");

    CreateOrderDto createOrderDto = CreateOrderDto.of(requestDto);
    Order createdOrder = Order.create(createOrderDto);

    Orderer orderer = createOrderer(createdOrder, requestDto.OrdererId());

    List<VendorOrder> vendorOrders = createVendorOrders(createdOrder, requestDto.ordersByVendor());

    createdOrder.addDependencies(orderer, vendorOrders);

    log.info("생성된 주문 저장: {}", createdOrder);
    return orderRepository.save(createdOrder);
  }

  private Orderer createOrderer(final Order createdOrder, final UUID ordererId) {
    log.info("주문자 엔티티 생성");

    GetCompanyResponse companyResponseDto = companyService.getCompanyById(ordererId);

    CreateOrdererDto requestDto = CreateOrdererDto.of(ordererId, companyResponseDto, createdOrder);
    return Orderer.create(requestDto);
  }

  private List<VendorOrder> createVendorOrders(
      final Order createdOrder,
      final List<OrdersByVendor> ordersByVendors
  ) {
    log.info("판매자 주문 목록 생성");

    log.warn("N+1 문제 발생!!!!!!!!!!!!!!!!!!!!!!!");
    List<VendorOrder> vendorOrders = new ArrayList<>();
    for (OrdersByVendor ordersByVendor : ordersByVendors) {
      VendorOrder vendorOrder = createVendorOrder(createdOrder, ordersByVendor);
      vendorOrders.add(vendorOrder);
    }

    return vendorOrders;
  }

  private VendorOrder createVendorOrder(
      final Order createdOrder,
      final OrdersByVendor ordersByVendor
  ) {
    log.info("판매자별 주문 엔티티 생성");

    CreateVendorOrderDto createVendorOrderDto =
        CreateVendorOrderDto.from(createdOrder, ordersByVendor);
    VendorOrder createdVendorOrder = VendorOrder.create(createVendorOrderDto);

    Vendor vendor = createVendor(createdVendorOrder, ordersByVendor);
    List<OrderProduct> orderProducts = createOrderProducts(createdVendorOrder, ordersByVendor);
    createdVendorOrder.addDependencies(vendor, orderProducts);

    var generateShippingDeadlineResponse = tryGenerateShippingDeadline(createdVendorOrder);
    createdVendorOrder.updateShippingDeadline(generateShippingDeadlineResponse.shippingDeadline());

    tryDeliveryRequest(createdVendorOrder);
    return createdVendorOrder;
  }

  private GenerateShippingDeadlineResponse tryGenerateShippingDeadline(
      VendorOrder createdVendorOrder
  ) {
    log.info("배송 예상일 생성 요청");
    var requestDto = GenerateShippingDeadlineRequestDto.from(createdVendorOrder);
    return aiService.generateShippingDeadline(requestDto);
  }

  private Vendor createVendor(
      final VendorOrder createdVendorOrder,
      final OrdersByVendor ordersByVendor
  ) {
    log.info("판매자 엔티티 생성");

    var companyResponseDto = companyService.getCompanyById(ordersByVendor.vendorId());

    CreateVendorDto createVendorDto =
        CreateVendorDto.of(ordersByVendor, companyResponseDto, createdVendorOrder);
    return Vendor.create(createVendorDto);
  }

  private List<OrderProduct> createOrderProducts(
      final VendorOrder createdVendorOrder,
      final OrdersByVendor ordersByVendor
  ) {
    log.info("주문 상품 목록 생성");

    log.warn("N+1 문제 발생!!!!!!!!!!!!!!!!!!!!!!!");
    List<OrderProduct> orderProducts = new ArrayList<>();
    for (OrderedProduct orderedProduct : ordersByVendor.orderItems()) {
      OrderProduct orderProduct = createOrderProduct(createdVendorOrder, orderedProduct);
      orderProducts.add(orderProduct);
    }
    return orderProducts;
  }

  private OrderProduct createOrderProduct(
      final VendorOrder vendorOrder,
      final OrderedProduct orderedProduct
  ) {
    log.info("주문 상품 엔티티 생성");

    var productResponseDto = productService.getProductById(orderedProduct.productId());

    if (isNotStockEnough(orderedProduct, productResponseDto)) {
      throw new OutOfStockProductOrderException();
    }
    tryDecreaseProductStock(orderedProduct);

    CreateOrderProductDto createOrderProductDto =
        CreateOrderProductDto.of(vendorOrder, orderedProduct, productResponseDto);
    return OrderProduct.create(createOrderProductDto);
  }

  private boolean isNotStockEnough(
      OrderedProduct orderedProduct,
      GetProductResponse productResponseDto
  ) {
    return orderedProduct.quantity() > productResponseDto.stock();
  }

  private void tryDecreaseProductStock(OrderedProduct orderedProduct) {
    try {
      decreaseProductStock(orderedProduct);
    } catch (ExternalApiException e) {
      log.warn("주문 상품 재고 감소 중 오류 발생: {}", e.getMessage());
      throw e;
    }
  }

  private void decreaseProductStock(OrderedProduct orderedProduct) {
    log.info("주문할 상품 재고 감소");
    var requestDto = DecreaseProductStockRequestDto.from(orderedProduct);
    productService.decreaseProductStock(requestDto);
  }

  private void tryDeliveryRequest(VendorOrder vendorOrder) {
    try {
      deliveryRequest(vendorOrder);
    } catch (ExternalApiException e) {
      log.warn("배송 요청 중 오류 발생: {}", e.getMessage());

      log.warn("N+1 문제 발생!!!!!!!!!!!!!!!!!!!!!!!");
      rollbackAllProduct(vendorOrder);
      throw e;
    }
  }

  private void rollbackAllProduct(VendorOrder vendorOrder) {
    for (OrderProduct orderProduct : vendorOrder.getOrderProducts()) {
      rollbackDecreaseProductStock(orderProduct);
    }
  }

  private void rollbackDecreaseProductStock(OrderProduct orderProduct) {
    log.info("주문할 상품 재고 감소 롤백");
    var requestDto = RollbackDecreaseProductStockRequestDto.from(orderProduct);
    productService.rollbackDecreaseProductStock(requestDto);
  }

  private void deliveryRequest(VendorOrder vendorOrder) {
    log.info("배송 요청");
    var requestDto = DeliveryRequestDto.from(vendorOrder);
    deliveryService.deliveryRequest(requestDto);
  }
}
