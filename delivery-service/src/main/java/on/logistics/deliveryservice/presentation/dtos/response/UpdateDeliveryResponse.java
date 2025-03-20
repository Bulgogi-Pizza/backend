package on.logistics.deliveryservice.presentation.dtos.response;

import java.util.UUID;

public record UpdateDeliveryResponse(UUID deliveryId) {

    public static UpdateDeliveryResponse of(UUID deliveryId) {
        return new UpdateDeliveryResponse(deliveryId);
    }

}
