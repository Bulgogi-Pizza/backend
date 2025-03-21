package on.logistics.deliveryservice.presentation.dtos.response;

import java.util.UUID;

public record UpdateDeliveryStatusHubArriveResponse(UUID deliveryId) {

    public static UpdateDeliveryStatusHubArriveResponse of(UUID deliveryId) {
        return new UpdateDeliveryStatusHubArriveResponse(deliveryId);
    }
}
