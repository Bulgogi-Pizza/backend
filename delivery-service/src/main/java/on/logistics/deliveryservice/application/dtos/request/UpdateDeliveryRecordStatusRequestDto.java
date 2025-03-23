package on.logistics.deliveryservice.application.dtos.request;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import on.logistics.deliveryservice.domain.enums.DeliveryRecordStatus;
import on.logistics.deliveryservice.presentation.dtos.request.UpdateDeliveryRecordStatusRequest;

public record UpdateDeliveryRecordStatusRequestDto(UUID deliveryRecordId,
                                                   DeliveryRecordStatus status,
                                                   HttpServletRequest httpServletRequest) {

    public static UpdateDeliveryRecordStatusRequestDto of(UUID deliveryRecordId,
        UpdateDeliveryRecordStatusRequest requestStatus, HttpServletRequest httpServletRequest) {
        return new UpdateDeliveryRecordStatusRequestDto(deliveryRecordId,
            requestStatus.deliveryRecordStatus(), httpServletRequest);
    }

}
