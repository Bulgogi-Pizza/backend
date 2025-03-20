package on.logistics.orderservice.domain.entity.dtos;

import on.logistics.orderservice.application.service.dtos.create.CreateOrderRequestDto;

public record CreateOrderDto(
    String destination,
    Long totalAmount
) {

    public static CreateOrderDto from(
        CreateOrderRequestDto requestDto
    ) {
        return new CreateOrderDto(
            requestDto.destination(),
            requestDto.totalAmount()
        );
    }
}
