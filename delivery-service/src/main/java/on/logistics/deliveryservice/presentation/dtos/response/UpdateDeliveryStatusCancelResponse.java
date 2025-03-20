package on.logistics.deliveryservice.presentation.dtos.response;

import java.util.UUID;

public record UpdateDeliveryStatusCancelResponse(UUID deliveryId) {

    public static UpdateDeliveryStatusCancelResponse of(UUID deliveryId) {
        return new UpdateDeliveryStatusCancelResponse(deliveryId);
    }

}
