package on.logistics.orderservice.application.service.dtos.returns.accept;

import java.util.UUID;
import on.logistics.orderservice.domain.entity.Order;

public record ReturnOrderResponseDto(
    UUID orderId
) {

  public static ReturnOrderResponseDto from(Order order) {
    return new ReturnOrderResponseDto(order.getId());
  }
}
