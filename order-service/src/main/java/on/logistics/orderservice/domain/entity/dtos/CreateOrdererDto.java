package on.logistics.orderservice.domain.entity.dtos;

import java.util.UUID;
import on.logistics.orderservice.application.service.dtos.create.CreateOrderRequestDto;
import on.logistics.orderservice.domain.entity.Order;

public record CreateOrdererDto(
    UUID companyId,
    String companyName,
    UUID userId,
    String userNickname,
    Order order
) {

  public static CreateOrdererDto of(
      CreateOrderRequestDto requestDto,
      Order order
  ) {
    return new CreateOrdererDto(
        requestDto.OrdererId(),
        requestDto.ordererName(),
        requestDto.ordererUserId(),
        requestDto.ordererUserNickname(),
        order
    );
  }
}
