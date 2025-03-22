package on.logistics.deliveryservice.application.dtos.request;

import java.util.UUID;
import on.logistics.deliveryservice.presentation.dtos.request.CreateDeliveryRecordRequest;

public record CreateDeliveryRecordRequestDto(UUID deliveryId, UUID deliveryRecordStartHubId,
                                             UUID deliveryRecordEndHubId, UUID userId) {

    public static CreateDeliveryRecordRequestDto from(CreateDeliveryRecordRequest request) {
        return new CreateDeliveryRecordRequestDto(request.deliveryId(),
            request.deliveryRecordStartHubId(), request.deliveryRecordEndHubId(),
            request.userId());
    }

}
