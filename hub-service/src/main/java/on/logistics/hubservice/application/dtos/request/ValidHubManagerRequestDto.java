package on.logistics.hubservice.application.dtos.request;

import java.util.UUID;
import lombok.Builder;
import on.logistics.hubservice.presentation.dtos.request.ValidHubManagerRequest;

@Builder
public record ValidHubManagerRequestDto(
    UUID userId,
    UUID hubId
) {

    public static ValidHubManagerRequestDto of(ValidHubManagerRequest request) {
        return ValidHubManagerRequestDto.builder()
            .userId(UUID.fromString(request.userId()))
            .hubId(UUID.fromString(request.hubId()))
            .build();
    }
}
