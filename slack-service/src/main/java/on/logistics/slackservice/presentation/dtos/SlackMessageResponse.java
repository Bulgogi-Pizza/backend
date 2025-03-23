package on.logistics.slackservice.presentation.dtos;

import java.util.UUID;
import on.logistics.slackservice.domain.entity.Slack;

public record SlackMessageResponse(
    UUID id
) {

    public static SlackMessageResponse from(Slack saved) {
        return new SlackMessageResponse(saved.getId());
    }
}
