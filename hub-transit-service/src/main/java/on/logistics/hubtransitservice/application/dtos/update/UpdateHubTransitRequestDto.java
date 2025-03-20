package on.logistics.hubtransitservice.application.dtos.update;

import java.util.UUID;
import on.logistics.hubtransitservice.presentation.dtos.update.UpdateHubTransitRequest;

public record UpdateHubTransitRequestDto(
    UUID transitId,
    UUID deliveryManagerId
) {

    public static UpdateHubTransitRequestDto of(UUID transitId, UpdateHubTransitRequest request) {
        return new UpdateHubTransitRequestDto(transitId, request.deliveryManagerId());
    }

}
