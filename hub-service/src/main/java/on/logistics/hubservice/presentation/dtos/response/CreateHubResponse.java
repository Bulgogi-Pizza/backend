package on.logistics.hubservice.presentation.dtos.response;

import java.util.UUID;
import lombok.Builder;

@Builder
public record CreateHubResponse(
    UUID hubId
) {

    public static CreateHubResponse of(UUID hubId) {
        return CreateHubResponse.builder()
            .hubId(hubId)
            .build();
    }
}
