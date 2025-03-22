package on.logistics.deliveryservice.application.dtos.request;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import on.logistics.deliveryservice.presentation.dtos.request.CreateDeliveryRecordRequest;

public record CreateDeliveryRecordRequestDto(UUID deliveryId, UUID deliveryRecordStartHubId,
                                             UUID deliveryRecordEndHubId, UUID userId,
                                             HttpServletRequest httpServletRequest) {

    public static CreateDeliveryRecordRequestDto from(CreateDeliveryRecordRequest request,
        HttpServletRequest httpServletRequest) {
        return new CreateDeliveryRecordRequestDto(request.deliveryId(),
            request.deliveryRecordStartHubId(), request.deliveryRecordEndHubId(),
            request.userId(), httpServletRequest);
    }

}
