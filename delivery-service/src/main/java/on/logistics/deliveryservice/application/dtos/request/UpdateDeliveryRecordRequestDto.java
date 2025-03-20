package on.logistics.deliveryservice.application.dtos.request;

import java.util.UUID;
import on.logistics.deliveryservice.presentation.dtos.request.UpdateDeliveryRecordRequest;

public record UpdateDeliveryRecordRequestDto(UUID deliveryRecordId, Long actualDistance,
                                             Long actualDuration) {

    public static UpdateDeliveryRecordRequestDto from(UUID id,
        UpdateDeliveryRecordRequest request) {
        return new UpdateDeliveryRecordRequestDto(id, request.actualDistance(),
            request.actualDuration());

    }
}