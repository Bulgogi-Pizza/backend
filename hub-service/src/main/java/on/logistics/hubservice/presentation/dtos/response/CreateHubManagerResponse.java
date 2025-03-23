package on.logistics.hubservice.presentation.dtos.response;

import java.util.UUID;
import lombok.Builder;

@Builder
public record CreateHubManagerResponse(
    UUID hubManagerId
) {

    public static CreateHubManagerResponse of(UUID hubManagerId) {
        return CreateHubManagerResponse.builder()
            .hubManagerId(hubManagerId)
            .build();
    }
}
