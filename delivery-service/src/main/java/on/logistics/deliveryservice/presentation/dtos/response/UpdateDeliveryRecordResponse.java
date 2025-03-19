package on.logistics.deliveryservice.presentation.dtos.response;

import java.util.UUID;

public record UpdateDeliveryRecordResponse(UUID deliveryRecordId) {

    public static UpdateDeliveryRecordResponse of(UUID deliveryRecordId) {
        return new UpdateDeliveryRecordResponse(deliveryRecordId);
    }

}
