package on.logistics.deliveryservice.presentation.dtos.response;

import java.util.UUID;

public record UpdateDeliveryRecordStatusResponse(UUID deliveryRecordId) {

    public static UpdateDeliveryRecordStatusResponse of(UUID deliveryRecordId) {
        return new UpdateDeliveryRecordStatusResponse(deliveryRecordId);
    }

}
