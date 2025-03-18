package on.logistics.orderservice.domain.entity.dtos;

import java.time.LocalDateTime;
import on.logistics.orderservice.application.service.dtos.create.CreateOrderRequestDto.OrdersByVendor;
import on.logistics.orderservice.domain.entity.Order;

public record CreateVendorOrderDto(
    Order order,
    Long totalAmount,
    LocalDateTime arrivalDeadline
) {

  public static CreateVendorOrderDto from(
      Order order,
      OrdersByVendor ordersByVendor
  ) {
    return new CreateVendorOrderDto(
        order,
        ordersByVendor.totalAmount(),
        ordersByVendor.arrivalDeadline()
    );
  }
}
