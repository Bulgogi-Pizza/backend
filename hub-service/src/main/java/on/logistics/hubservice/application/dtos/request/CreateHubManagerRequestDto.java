package on.logistics.hubservice.application.dtos.request;

import java.util.UUID;
import lombok.Builder;
import on.logistics.hubservice.presentation.dtos.request.CreateHubManagerRequest;

@Builder
public record CreateHubManagerRequestDto(
    UUID userId,
    UUID hubId
) {

    public static CreateHubManagerRequestDto of(CreateHubManagerRequest request) {
        return CreateHubManagerRequestDto.builder()
            .userId(UUID.fromString(request.userId()))
            .hubId(UUID.fromString(request.hubId()))
            .build();
    }
}
