package on.logistics.deliveryservice.presentation.dtos.response;

import java.util.UUID;
import on.logistics.deliveryservice.domain.entity.DeliveryRecord;
import on.logistics.deliveryservice.domain.enums.DeliveryRecordStatus;

public record GetDeliveryRecordResponse(UUID deliveryId, UUID deliveryRecordId,
                                        Long deliveryRecordSequence,
                                        DeliveryRecordStatus deliveryRecordStatus,
                                        UUID deliveryRecordStartHubId, UUID deliveryRecordEndHubId,
                                        Long estimatedDistance, Long estimatedDuration,
                                        Long actualDistance, Long actualDuration) {

    public static GetDeliveryRecordResponse from(DeliveryRecord deliveryRecord) {
        return new GetDeliveryRecordResponse(deliveryRecord.getDelivery().getId(),
            deliveryRecord.getId(),
            deliveryRecord.getSequence(), deliveryRecord.getStatus(),
            deliveryRecord.getStartHubId(), deliveryRecord.getEndHubId(),
            deliveryRecord.getEstimatedDistance(), deliveryRecord.getEstimatedDuration(),
            deliveryRecord.getActualDistance(), deliveryRecord.getActualDuration());
    }

}
