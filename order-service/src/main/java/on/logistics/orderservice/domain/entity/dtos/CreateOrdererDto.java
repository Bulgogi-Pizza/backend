package on.logistics.orderservice.domain.entity.dtos;

import java.util.UUID;
import on.logistics.orderservice.application.service.dtos.create.CreateOrderRequestDto;
import on.logistics.orderservice.domain.entity.Order;
import on.logistics.orderservice.infrastructure.clients.hub.dtos.GetHubByIdResponseDto;

public record CreateOrdererDto(
    UUID companyId,
    String companyName,
    UUID userId,
    String userNickname,
    UUID ordererHubId,
    String ordererHubName,
    Order order
) {

    public static CreateOrdererDto of(
        CreateOrderRequestDto requestDto,
        Order order,
        GetHubByIdResponseDto hubManagerByUserId
    ) {
        return new CreateOrdererDto(
            requestDto.OrdererId(),
            requestDto.ordererName(),
            requestDto.ordererUserId(),
            requestDto.ordererUserNickname(),
            hubManagerByUserId.id(),
            hubManagerByUserId.hubName(),
            order
        );
    }
}
