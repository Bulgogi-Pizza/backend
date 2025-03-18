package on.logistics.deliveryservice.presentation.dtos.response;

import java.util.UUID;

public record UpdateDeliveryStatusHubMovingResponse(UUID deliveryId) {

    public static UpdateDeliveryStatusHubMovingResponse of(UUID deliveryId) {
        return new UpdateDeliveryStatusHubMovingResponse(deliveryId);
    }

}
