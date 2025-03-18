package on.logistics.orderservice.domain.entity.dtos;

import java.util.List;
import on.logistics.orderservice.application.dtos.create.CreateOrderRequestDto;
import on.logistics.orderservice.domain.entity.Orderer;
import on.logistics.orderservice.domain.entity.VendorOrder;

public record CreateOrderDto(
    String destination,
    Long totalAmount
) {

  public static CreateOrderDto of(
      CreateOrderRequestDto requestDto
  ) {
    return new CreateOrderDto(
        requestDto.destination(),
        requestDto.totalAmount()
    );
  }
}
