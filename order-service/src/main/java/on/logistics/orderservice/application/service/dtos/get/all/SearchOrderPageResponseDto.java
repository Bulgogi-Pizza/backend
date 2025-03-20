package on.logistics.orderservice.application.service.dtos.get.all;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import on.logistics.orderservice.domain.entity.Order;
import on.logistics.orderservice.domain.enums.OrderStatus;

public record SearchOrderPageResponseDto(
    UUID orderId,
    LocalDateTime orderCreatedAt,
    UUID ordererUserId,
    String ordererName,
    Long totalAmount,
    List<OrdersByVendor> ordersByVendor
) {

  private record OrdersByVendor(
      UUID vendorOrderId,
      UUID vendorId,
      String vendorName,
      Long amountByVendor,
      OrderStatus orderStatus,
      LocalDateTime arrivalDateTime
  ) {

  }

  public static SearchOrderPageResponseDto from(Order order) {
    return new SearchOrderPageResponseDto(
        order.getId(),
        order.getCreatedAt(),
        order.getOrderer().getId(),
        order.getOrderer().getCompanyName().getValue(),
        order.getTotalAmount(),
        order.getVendorOrders().stream()
            .map(ordersByVendor -> new OrdersByVendor(
                ordersByVendor.getId(),
                ordersByVendor.getVendor().getId(),
                ordersByVendor.getVendor().getName().getValue(),
                ordersByVendor.getAmountByVendor(),
                ordersByVendor.getStatus(),
                ordersByVendor.getArrivalDeadline()
            ))
            .toList()
    );
  }
}
