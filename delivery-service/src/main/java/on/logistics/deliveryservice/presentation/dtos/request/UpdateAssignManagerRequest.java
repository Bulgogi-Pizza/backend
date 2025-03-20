package on.logistics.deliveryservice.presentation.dtos.request;

import java.util.UUID;
import on.logistics.deliveryservice.application.dtos.request.UpdateAssignManagerRequestDto;

public record UpdateAssignManagerRequest(UUID companyDeliveryManagerId) {

    public static UpdateAssignManagerRequestDto from(UUID id,
        UpdateAssignManagerRequest updateAssignManagerRequest) {
        return new UpdateAssignManagerRequestDto(id,
            updateAssignManagerRequest.companyDeliveryManagerId());
    }
}
