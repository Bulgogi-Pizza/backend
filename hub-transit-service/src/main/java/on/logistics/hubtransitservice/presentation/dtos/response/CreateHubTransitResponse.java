package on.logistics.hubtransitservice.presentation.dtos.response;

import java.util.UUID;
import lombok.Builder;
import on.logistics.hubtransitservice.application.dtos.response.CreateHubTransitResponseDto;

@Builder
public record CreateHubTransitResponse(
    UUID transitId
) {

    public static CreateHubTransitResponse from(CreateHubTransitResponseDto dto) {
        return CreateHubTransitResponse.builder()
            .transitId(dto.transitId())
            .build();
    }
}
