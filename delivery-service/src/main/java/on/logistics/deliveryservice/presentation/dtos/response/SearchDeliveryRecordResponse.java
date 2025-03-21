package on.logistics.deliveryservice.presentation.dtos.response;

import java.util.UUID;
import on.logistics.deliveryservice.domain.entity.DeliveryRecord;
import on.logistics.deliveryservice.domain.enums.DeliveryRecordStatus;

public record SearchDeliveryRecordResponse(UUID deliveryId, UUID deliveryRecordId,
                                           Long deliveryRecordSequence,
                                           DeliveryRecordStatus deliveryRecordStatus,
                                           UUID deliveryRecordStartHubId,
                                           UUID deliveryRecordEndHubId, UUID deliveryManagerId) {

    public static SearchDeliveryRecordResponse from(DeliveryRecord deliveryRecord) {
        return new SearchDeliveryRecordResponse(deliveryRecord.getDelivery().getId(),
            deliveryRecord.getId(), deliveryRecord.getSequence(), deliveryRecord.getStatus(),
            deliveryRecord.getStartHubId(), deliveryRecord.getEndHubId(),
            deliveryRecord.getDeliveryManagerId());
    }
}
