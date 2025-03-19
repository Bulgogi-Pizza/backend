package on.logistics.deliveryservice.presentation.dtos.response;

import java.util.UUID;

public record UpdateDeliveryRecordStatusResponse(UUID deliveryId) {

    public static UpdateDeliveryRecordStatusResponse of(UUID deliveryId) {
        return new UpdateDeliveryRecordStatusResponse(deliveryId);
    }

}
