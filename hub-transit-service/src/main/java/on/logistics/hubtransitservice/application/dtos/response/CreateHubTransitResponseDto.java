package on.logistics.hubtransitservice.application.dtos.response;

import java.util.UUID;
import lombok.Builder;
import on.logistics.hubtransitservice.domain.entity.HubTransit;
import on.logistics.hubtransitservice.presentation.dtos.response.CreateHubTransitResponse;

@Builder
public record CreateHubTransitResponseDto(
    UUID transitId
) {

    public static CreateHubTransitResponse from(HubTransit hubTransit) {
        return CreateHubTransitResponse.builder()
            .transitId(hubTransit.getId())
            .build();
    }
}
