package on.logistics.orderservice.application.clients.delivery.feign.dtos;

import java.util.UUID;
import on.logistics.orderservice.application.clients.delivery.dtos.DeliveryRequestDto;

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
