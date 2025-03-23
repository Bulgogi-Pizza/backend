package on.logistics.hubtransitservice.application.dtos.request;

import java.util.UUID;
import lombok.Builder;
import on.logistics.hubtransitservice.presentation.dtos.request.UpdateHubTransitRequest;

@Builder
public record UpdateHubTransitRequestDto(
    UUID transitId,
    UUID userId
) {

    public static UpdateHubTransitRequestDto of(UUID transitId, UpdateHubTransitRequest request) {
        return UpdateHubTransitRequestDto.builder()
            .transitId(transitId)
            .userId(request.userId())
            .build();
    }

}
