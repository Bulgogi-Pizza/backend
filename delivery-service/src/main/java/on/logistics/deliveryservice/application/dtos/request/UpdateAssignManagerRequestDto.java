package on.logistics.deliveryservice.application.dtos.request;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import on.logistics.deliveryservice.presentation.dtos.request.UpdateAssignManagerRequest;

public record UpdateAssignManagerRequestDto(UUID deliveryId, UUID userId,
                                            HttpServletRequest httpServletRequest) {

    public static UpdateAssignManagerRequestDto from(UUID id,
        UpdateAssignManagerRequest updateAssignManagerRequest,
        HttpServletRequest httpServletRequest) {
        return new UpdateAssignManagerRequestDto(id,
            updateAssignManagerRequest.userId(), httpServletRequest);
    }
}
