package on.logistics.deliveryservice.application.dtos.request;

import java.util.UUID;
import on.logistics.deliveryservice.presentation.dtos.request.CreateDeliveryRecordRequest;

public record CreateDeliveryRecordRequestDto(UUID deliveryId, UUID deliveryRecordStartHubId,
                                             UUID deliveryRecordEndHubId, UUID deliveryManagerId) {

    public static CreateDeliveryRecordRequestDto from(CreateDeliveryRecordRequest request) {
        return new CreateDeliveryRecordRequestDto(request.deliveryId(),
            request.deliveryRecordStartHubId(), request.deliveryRecordStartHubId(),
            request.deliveryManagerId());
    }

}
