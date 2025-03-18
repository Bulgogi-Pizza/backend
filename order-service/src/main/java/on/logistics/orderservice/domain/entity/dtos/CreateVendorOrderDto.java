package on.logistics.orderservice.domain.entity.dtos;

import java.time.LocalDateTime;
import java.util.List;
import on.logistics.orderservice.application.dtos.create.CreateOrderRequestDto.OrdersByVendor;
import on.logistics.orderservice.domain.entity.Order;
import on.logistics.orderservice.domain.entity.OrderProduct;
import on.logistics.orderservice.domain.entity.Vendor;

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
