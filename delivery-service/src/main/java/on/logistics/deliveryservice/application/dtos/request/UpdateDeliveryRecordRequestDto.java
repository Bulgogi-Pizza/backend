package on.logistics.deliveryservice.application.dtos.request;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import on.logistics.deliveryservice.presentation.dtos.request.UpdateDeliveryRecordRequest;

public record UpdateDeliveryRecordRequestDto(UUID deliveryRecordId, Long actualDistance,
                                             Long actualDuration,
                                             HttpServletRequest httpServletRequest) {

    public static UpdateDeliveryRecordRequestDto from(UUID id,
        UpdateDeliveryRecordRequest request, HttpServletRequest httpServletRequest) {
        return new UpdateDeliveryRecordRequestDto(id, request.actualDistance(),
            request.actualDuration(), httpServletRequest);

    }
}