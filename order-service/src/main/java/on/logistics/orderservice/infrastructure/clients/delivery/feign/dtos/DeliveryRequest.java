package on.logistics.orderservice.infrastructure.clients.delivery.feign.dtos;

import java.util.UUID;
import on.logistics.orderservice.infrastructure.clients.delivery.dtos.DeliveryRequestDto;

public record DeliveryRequest(
    UUID orderId,
    String destination,
    UUID startHubId
) {

    public static DeliveryRequest from(DeliveryRequestDto requestDto) {
        return new DeliveryRequest(
            requestDto.orderId(),
            requestDto.destination(),
            requestDto.startHubId()
        );
    }
}
