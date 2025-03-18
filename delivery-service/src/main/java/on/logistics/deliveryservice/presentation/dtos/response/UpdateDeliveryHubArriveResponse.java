package on.logistics.deliveryservice.presentation.dtos.response;

import java.util.UUID;

public record UpdateDeliveryHubArriveResponse(UUID deliveryId) {

    public static UpdateDeliveryHubArriveResponse of(UUID deliveryId) {
        return new UpdateDeliveryHubArriveResponse(deliveryId);
    }
}
