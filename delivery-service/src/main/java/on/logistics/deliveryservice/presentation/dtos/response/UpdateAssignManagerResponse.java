package on.logistics.deliveryservice.presentation.dtos.response;

import java.util.UUID;

public record UpdateAssignManagerResponse(UUID deliveryId) {

    public static UpdateAssignManagerResponse of(UUID deliveryId) {
        return new UpdateAssignManagerResponse(deliveryId);
    }
}
