package on.logistics.orderservice.application.service.dtos.get.detail;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import on.logistics.orderservice.domain.entity.Order;
import on.logistics.orderservice.domain.enums.OrderStatus;

public record GetOrderDetailResponseDto(
    UUID orderId,
    LocalDateTime orderCreatedAt,
    Long totalAmount,
    UUID ordererCompanyId,
    String ordererCompanyName,
    UUID ordererUserId,
    String ordererUserNickname,
    String destination,
    List<OrdersByVendor> ordersByVendor
) {

  public record OrdersByVendor(
      UUID vendorOrderId,
      Long amountByVendor,
      LocalDateTime arrivalDeadline,
      UUID vendorCompanyId,
      String vendorCompanyName,
      UUID vendorHubId,
      String vendorHubName,
      OrderStatus orderStatus,
      LocalDateTime shippedAt,
      List<OrderedProducts> orderedProducts
  ) {

    public record OrderedProducts(
        UUID productId,
        String name,
        Long price,
        Long quantity
    ) {

    }
  }


  public static GetOrderDetailResponseDto from(Order order) {
    return new GetOrderDetailResponseDto(
        order.getId(),
        order.getCreatedAt(),
        order.getTotalAmount(),
        order.getOrderer().getCompanyId(),
        order.getOrderer().getCompanyName().getValue(),
        order.getOrderer().getUserId(),
        order.getOrderer().getUserNickname().getValue(),
        order.getDestination(),
        order.getVendorOrders().stream()
            .map(vendorOrder -> new OrdersByVendor(
                vendorOrder.getId(),
                vendorOrder.getTotalAmount(),
                vendorOrder.getArrivalDeadline(),
                vendorOrder.getVendor().getCompanyId(),
                vendorOrder.getVendor().getName().getValue(),
                vendorOrder.getVendor().getVendorHubId(),
                vendorOrder.getVendor().getVendorHubName().getValue(),
                vendorOrder.getStatus(),
                vendorOrder.getShippedAt(),
                vendorOrder.getOrderProducts().stream()
                    .map(orderedProduct -> new OrdersByVendor.OrderedProducts(
                        orderedProduct.getProductId(),
                        orderedProduct.getName().getValue(),
                        orderedProduct.getPrice().getValue(),
                        orderedProduct.getQuantity().getValue()
                    ))
                    .toList()
            ))
            .toList()
    );
  }
}
