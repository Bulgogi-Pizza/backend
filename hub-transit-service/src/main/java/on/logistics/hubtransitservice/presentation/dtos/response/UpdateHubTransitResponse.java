package on.logistics.hubtransitservice.presentation.dtos.response;

import java.util.UUID;
import lombok.Builder;
import on.logistics.hubtransitservice.domain.entity.HubTransit;

@Builder
public record UpdateHubTransitResponse(
    UUID transitId,
    UUID userId
) {

    public static UpdateHubTransitResponse from(HubTransit hubTransit) {
        return UpdateHubTransitResponse.builder()
            .transitId(hubTransit.getId())
            .userId(hubTransit.getUserId())
            .build();
    }

}
