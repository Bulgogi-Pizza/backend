package on.logistics.hubtransitservice.application.dtos.request;

import java.util.UUID;
import lombok.Builder;

@Builder
public record GetNextHubRequestDto(
    UUID transitId,
    UUID currentHubId
) {

    public static GetNextHubRequestDto of(UUID transitId, UUID currentHubId) {
        return GetNextHubRequestDto.builder()
            .transitId(transitId)
            .currentHubId(currentHubId)
            .build();
    }

}
