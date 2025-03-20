package on.logistics.deliveryservice.presentation.dtos.request;

import java.util.UUID;
import on.logistics.deliveryservice.application.dtos.request.UpdateDeliveryRequestDto;

public record UpdateDeliveryRequest(UUID deliveryId, String destination) {

    public static UpdateDeliveryRequestDto from(UUID deliveryId, UpdateDeliveryRequest request) {
        return new UpdateDeliveryRequestDto(deliveryId, request.deliveryId(),
            request.destination());
    }
}
