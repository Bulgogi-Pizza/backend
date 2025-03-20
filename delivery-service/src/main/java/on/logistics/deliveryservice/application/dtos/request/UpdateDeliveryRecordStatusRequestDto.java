package on.logistics.deliveryservice.application.dtos.request;

import java.util.UUID;
import on.logistics.deliveryservice.domain.enums.DeliveryRecordStatus;
import on.logistics.deliveryservice.presentation.dtos.request.UpdateDeliveryRecordStatusRequest;

public record UpdateDeliveryRecordStatusRequestDto(UUID deliveryRecordId,
                                                   DeliveryRecordStatus status) {

    public static UpdateDeliveryRecordStatusRequestDto of(UUID deliveryRecordId,
        UpdateDeliveryRecordStatusRequest requestStatus) {
        return new UpdateDeliveryRecordStatusRequestDto(deliveryRecordId,
            requestStatus.deliveryRecordStatus());
    }

}
