package on.logistics.deliveryservice.presentation.dtos.response;

import java.util.UUID;

public record CreateDeliveryRecordResponse(UUID deliveryRecordId) {

    public static CreateDeliveryRecordResponse of(UUID deliveryRecordId) {
        return new CreateDeliveryRecordResponse(deliveryRecordId);
    }

}
