package on.logistics.orderservice.application.service;

import static on.logistics.orderservice.exception.OrderException.OutOfStockProductOrderException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.orderservice.application.service.dtos.cancel.CancelOrderRequestDto;
import on.logistics.orderservice.application.service.dtos.cancel.CancelOrderResponseDto;
import on.logistics.orderservice.application.service.dtos.create.CreateOrderRequestDto;
import on.logistics.orderservice.application.service.dtos.create.CreateOrderRequestDto.OrdersByVendor;
import on.logistics.orderservice.application.service.dtos.create.CreateOrderRequestDto.OrdersByVendor.OrderedProduct;
import on.logistics.orderservice.application.service.dtos.create.CreateOrderResponseDto;
import on.logistics.orderservice.application.service.dtos.get.all.SearchOrderPageRequestDto;
import on.logistics.orderservice.application.service.dtos.get.all.SearchOrderPageResponseDto;
import on.logistics.orderservice.application.service.dtos.get.detail.GetOrderDetailRequestDto;
import on.logistics.orderservice.application.service.dtos.get.detail.GetOrderDetailResponseDto;
import on.logistics.orderservice.application.service.dtos.returns.accept.ReturnOrderRequestDto;
import on.logistics.orderservice.application.service.dtos.returns.accept.ReturnOrderResponseDto;
import on.logistics.orderservice.application.service.dtos.returns.denied.ReturnRequestDeniedRequestDto;
import on.logistics.orderservice.application.service.dtos.returns.denied.ReturnRequestDeniedResponseDto;
import on.logistics.orderservice.application.service.dtos.returns.request.ReturnRequestRequestDto;
import on.logistics.orderservice.application.service.dtos.returns.request.ReturnRequestResponseDto;
import on.logistics.orderservice.application.service.dtos.update.UpdateOrderRequestDto;
import on.logistics.orderservice.application.service.dtos.update.UpdateOrderResponseDto;
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
import on.logistics.orderservice.domain.enums.OrderStatus;
import on.logistics.orderservice.domain.repository.OrderRepository;
import on.logistics.orderservice.domain.repository.dtos.SearchOrderPageDto;
import on.logistics.orderservice.exception.OrderException.OrderNotFoundException;
import on.logistics.orderservice.exception.OrderException.OrderProductNotFoundException;
import on.logistics.orderservice.exception.OrderException.VendorOrderNotFoundException;
import on.logistics.orderservice.global.application.dtos.PageDto;
import on.logistics.orderservice.infrastructure.clients.ai.dtos.GenerateShippingDeadlineRequestDto;
import on.logistics.orderservice.infrastructure.clients.ai.feign.dtos.GenerateShippingDeadlineResponse;
import on.logistics.orderservice.infrastructure.clients.delivery.dtos.DeliveryRequestDto;
import on.logistics.orderservice.infrastructure.clients.exception.ExternalApiException;
import on.logistics.orderservice.infrastructure.clients.exception.ExternalApiException.ExternalApiBadRequestException;
import on.logistics.orderservice.infrastructure.clients.product.dtos.DecreaseProductStockRequestDto;
import on.logistics.orderservice.infrastructure.clients.product.dtos.RollbackDecreaseProductStockRequestDto;
import on.logistics.orderservice.presentation.dtos.delete.DeleteOrderRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  private final ProductService productService;
  private final DeliveryService deliveryService;
  private final AIService aiService;
  private final CompanyService companyService;

  @Transactional
  @Override
  public CreateOrderResponseDto createOrder(final CreateOrderRequestDto requestDto) {
    log.info("주문 생성 요청: {}", requestDto);

    var createOrderDto = CreateOrderDto.from(requestDto);
    Order createdOrder = Order.create(createOrderDto);

    Orderer orderer = createOrderer(createdOrder, requestDto);
    List<VendorOrder> vendorOrders = createVendorOrders(createdOrder, requestDto.ordersByVendor());

    createdOrder.addDependencies(orderer, vendorOrders);

    log.info("생성된 주문 저장: {}", createdOrder);
    Order savedOrder = orderRepository.save(createdOrder);

    return CreateOrderResponseDto.from(savedOrder);
  }

  private Orderer createOrderer(final Order createdOrder, final CreateOrderRequestDto requestDto) {
    log.info("주문자 엔티티 생성");

    CreateOrdererDto createOrdererDto = CreateOrdererDto.of(requestDto, createdOrder);
    return Orderer.create(createOrdererDto);
  }

  private List<VendorOrder> createVendorOrders(
      final Order createdOrder,
      final List<OrdersByVendor> ordersByVendors
  ) {
    log.info("판매자 주문 목록 생성");

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

    var createVendorOrderDto = CreateVendorOrderDto.of(createdOrder, ordersByVendor);
    VendorOrder createdVendorOrder = VendorOrder.create(createVendorOrderDto);

    Vendor vendor = createVendor(createdVendorOrder, ordersByVendor);
    List<OrderProduct> orderProducts = createOrderProducts(createdVendorOrder, ordersByVendor);
    createdVendorOrder.addDependencies(vendor, orderProducts);

    var generateShippingDeadlineResponse = tryGenerateShippingDeadline(createdVendorOrder);
    createdVendorOrder.updateShippingDeadline(generateShippingDeadlineResponse.shippingDeadline());

    tryDeliveryRequest(createdVendorOrder);
    return createdVendorOrder;
  }

  private Vendor createVendor(
      final VendorOrder createdVendorOrder,
      final OrdersByVendor ordersByVendor
  ) {
    log.info("판매자 엔티티 생성");
    var createVendorDto = CreateVendorDto.of(createdVendorOrder, ordersByVendor);
    return Vendor.create(createVendorDto);
  }

  private List<OrderProduct> createOrderProducts(
      final VendorOrder createdVendorOrder,
      final OrdersByVendor ordersByVendor
  ) {
    log.info("주문 상품 목록 생성");

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

    tryDecreaseProductStock(orderedProduct);

    var createOrderProductDto = CreateOrderProductDto.of(vendorOrder, orderedProduct);
    return OrderProduct.create(createOrderProductDto);
  }

  private GenerateShippingDeadlineResponse tryGenerateShippingDeadline(
      final VendorOrder createdVendorOrder
  ) {
    log.info("배송 예상일 생성 요청");
    var requestDto = GenerateShippingDeadlineRequestDto.from(createdVendorOrder);
    try {
      return aiService.generateShippingDeadline(requestDto);
    } catch (ExternalApiException e) {
      log.warn("배송 예상일 생성 중 오류 발생: {}", e.getMessage());
      throw e;
    }
  }

  private void tryDecreaseProductStock(final OrderedProduct orderedProduct) {
    try {
      decreaseProductStock(orderedProduct);
    } catch (ExternalApiBadRequestException e) {
      log.warn("주문 상품 재고 감소 요청 데이터 오류: {}", e.getMessage());
      throw new OutOfStockProductOrderException();
    } catch (ExternalApiException e) {
      log.warn("주문 상품 재고 감소 중 오류 발생: {}", e.getMessage());
      throw e;
    }
  }

  private void decreaseProductStock(final OrderedProduct orderedProduct) {
    log.info("주문할 상품 재고 감소");
    var requestDto = DecreaseProductStockRequestDto.from(orderedProduct);
    productService.decreaseProductStock(requestDto);
  }

  private void tryDeliveryRequest(final VendorOrder vendorOrder) {
    try {
      deliveryRequest(vendorOrder);
    } catch (ExternalApiException e) {
      log.warn("배송 요청 중 오류 발생: {}", e.getMessage());
      rollbackAllProduct(vendorOrder);
      throw e;
    }
  }

  private void rollbackAllProduct(final VendorOrder vendorOrder) {
    for (OrderProduct orderProduct : vendorOrder.getOrderProducts()) {
      rollbackDecreaseProductStock(orderProduct);
    }
  }

  private void rollbackDecreaseProductStock(final OrderProduct orderProduct) {
    log.info("주문할 상품 재고 감소 롤백");
    var requestDto = RollbackDecreaseProductStockRequestDto.from(orderProduct);
    productService.rollbackDecreaseProductStock(requestDto);
  }

  private void deliveryRequest(final VendorOrder vendorOrder) {
    log.info("배송 요청");
    vendorOrder.ship();
    var requestDto = DeliveryRequestDto.from(vendorOrder);
    deliveryService.deliveryRequest(requestDto);
  }

  @Override
  public PageDto<SearchOrderPageResponseDto> searchOrderPage(
      final SearchOrderPageRequestDto requestDto
  ) {
    log.info("주문자별 주문 목록 조회 요청: {}", requestDto);

    SearchOrderPageDto searchOrderPageDto = SearchOrderPageDto.from(requestDto);
    Page<Order> allByOrdererUserId = orderRepository.searchOrderPage(searchOrderPageDto);
    Page<SearchOrderPageResponseDto> getOrderPageByOrdererUserIdResponseDtoPage =
        allByOrdererUserId.map(SearchOrderPageResponseDto::from);
    return PageDto.from(getOrderPageByOrdererUserIdResponseDtoPage);
  }

  @Override
  public GetOrderDetailResponseDto getOrderDetail(final GetOrderDetailRequestDto requestDto) {
    log.info("주문 상세 조회 요청: {}", requestDto);

    Order order = orderRepository.findOrderById(requestDto.orderId())
        .orElseThrow(OrderNotFoundException::new);

    return GetOrderDetailResponseDto.from(order);
  }

  @Transactional
  @Override
  public UpdateOrderResponseDto updateOrder(final UpdateOrderRequestDto requestDto) {
    log.info("주문 수정 요청: {}", requestDto);

    Order order = orderRepository.findOrderById(requestDto.orderId())
        .orElseThrow(OrderNotFoundException::new);

    updateVendorOrders(order, requestDto.ordersByVendor());

    return UpdateOrderResponseDto.from(order);
  }

  private void updateVendorOrders(
      final Order order,
      final List<UpdateOrderRequestDto.OrdersByVendor> ordersByVendor
  ) {
    for (UpdateOrderRequestDto.OrdersByVendor orderByVendor : ordersByVendor) {
      VendorOrder vendorOrder = getIsBeforeShippedVendorOrder(
          order, orderByVendor.orderIdByVendor());

      vendorOrder.updateArrivalDeadline(orderByVendor.arrivalDeadline());

      updateOrderProducts(vendorOrder, orderByVendor.orderedProducts());
    }
  }

  private void updateOrderProducts(
      final VendorOrder vendorOrder,
      final List<UpdateOrderRequestDto.OrdersByVendor.OrderedProduct> orderedProducts
  ) {
    for (var product : orderedProducts) {
      OrderProduct orderProduct = vendorOrder.getOrderProducts().stream()
          .filter(op -> op.getProductId().equals(product.productId()))
          .findFirst()
          .orElseThrow(OrderProductNotFoundException::new);

      orderProduct.updateQuantity(product.quantity());
    }
  }

  @Transactional
  @Override
  public CancelOrderResponseDto cancelVendorOrder(final CancelOrderRequestDto requestDto) {
    log.info("주문 취소 요청: {}", requestDto);

    Order order = orderRepository.findOrderById(requestDto.orderId())
        .orElseThrow(OrderNotFoundException::new);

    VendorOrder vendorOrder = getIsBeforeShippedVendorOrder(
        order, requestDto.vendorOrderId());

    vendorOrder.cancel();
    return CancelOrderResponseDto.from(vendorOrder);
  }

  private static VendorOrder getIsBeforeShippedVendorOrder(
      final Order order,
      final UUID requestDto
  ) {
    return order.getVendorOrders().stream()
        .filter(vo -> vo.getId().equals(requestDto))
        .filter(vo -> OrderStatus.isBeforeShipped(vo.getStatus()))
        .findFirst()
        .orElseThrow(VendorOrderNotFoundException::new);
  }

  @Transactional
  @Override
  public void deleteVendorOrder(final DeleteOrderRequestDto requestDto) {
    log.info("주문 삭제 요청: {}", requestDto);

    Order order = orderRepository.findOrderById(requestDto.orderId())
        .orElseThrow(OrderNotFoundException::new);

    VendorOrder vendorOrder = getIsAfterDeliveredVendorOrder(order, requestDto.vendorOrderId());

    order.removeVendorOrder(vendorOrder);
  }

  @Transactional
  @Override
  public ReturnRequestResponseDto requestReturn(final ReturnRequestRequestDto requestDto) {
    log.info("반품 요청: {}", requestDto);

    Order order = orderRepository.findOrderById(requestDto.orderId())
        .orElseThrow(OrderNotFoundException::new);

    VendorOrder vendorOrder = getIsAfterDeliveredVendorOrder(
        order, requestDto.vendorOrderId());

    vendorOrder.requestReturn();

    return ReturnRequestResponseDto.from(vendorOrder);
  }

  private static VendorOrder getIsAfterDeliveredVendorOrder(
      final Order order,
      final UUID vendorOrderId
  ) {
    return order.getVendorOrders().stream()
        .filter(vo -> vo.getId().equals(vendorOrderId))
        .filter(vo -> OrderStatus.isAfterDelivered(vo.getStatus()))
        .findFirst()
        .orElseThrow(VendorOrderNotFoundException::new);
  }

  @Transactional
  @Override
  public ReturnRequestDeniedResponseDto denyReturnRequest(
      final ReturnRequestDeniedRequestDto requestDto
  ) {
    log.info("반품 거부 요청: {}", requestDto);

    Order order = orderRepository.findOrderById(requestDto.orderId())
        .orElseThrow(OrderNotFoundException::new);

    VendorOrder vendorOrder = getIsReturnRequestedVendorOrder(requestDto.vendorOrderId(), order);
    vendorOrder.denyReturn();

    return ReturnRequestDeniedResponseDto.from(vendorOrder);
  }

  @Transactional
  @Override
  public ReturnOrderResponseDto returnOrder(final ReturnOrderRequestDto requestDto) {
    log.info("반품 완료 요청: {}", requestDto);

    Order order = orderRepository.findOrderById(requestDto.orderId())
        .orElseThrow(OrderNotFoundException::new);

    VendorOrder vendorOrder = getIsReturnRequestedVendorOrder(requestDto.vendorOrderId(), order);
    vendorOrder.returnOrder();

    Order returnedOrder = Order.create(order.getOrderer(), vendorOrder);
    VendorOrder returnedVendorOrder = returnedOrder.getVendorOrders().get(0);

    tryDeliveryRequest(returnedVendorOrder);
    returnedVendorOrder.ship();

    Order savedReturnOrder = orderRepository.save(returnedOrder);
    return ReturnOrderResponseDto.from(savedReturnOrder);
  }

  private static VendorOrder getIsReturnRequestedVendorOrder(
      final UUID vendorOrderId,
      final Order order
  ) {
    return order.getVendorOrders().stream()
        .filter(vo -> vo.getId().equals(vendorOrderId))
        .filter(vo -> OrderStatus.isReturnRequested(vo.getStatus()))
        .findFirst()
        .orElseThrow(VendorOrderNotFoundException::new);
  }
}
