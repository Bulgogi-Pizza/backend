package on.logistics.slackservice.presentation.dtos;

import java.util.UUID;
import lombok.Builder;
import on.logistics.slackservice.domain.entity.Slack;

@Builder
public record UpdateSlackMessageResponse(
    UUID id,
    String message
) {

    public static UpdateSlackMessageResponse from(Slack updated) {
        return UpdateSlackMessageResponse.builder()
            .id(updated.getId())
            .message(updated.getMessage())
            .build();
    }
}
