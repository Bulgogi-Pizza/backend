package on.logistics.orderservice.application.dtos.create;

import java.util.UUID;
import on.logistics.orderservice.domain.entity.Order;

public record CreateOrderResponseDto(
    UUID id
) {

  public static CreateOrderResponseDto from(Order order) {
    return new CreateOrderResponseDto(
        order.getId()
    );
  }
}
