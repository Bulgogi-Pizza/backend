package on.logistics.orderservice.application.service.dtos.cancel;

import java.util.UUID;
import on.logistics.orderservice.domain.entity.Order;

public record CancelOrderResponseDto(
    UUID orderId
) {

  public static CancelOrderResponseDto from(Order order) {
    return new CancelOrderResponseDto(order.getId());
  }
}
