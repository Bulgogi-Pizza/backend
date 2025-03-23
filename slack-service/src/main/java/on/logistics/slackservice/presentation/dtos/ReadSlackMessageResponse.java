package on.logistics.slackservice.presentation.dtos;

import java.util.UUID;
import lombok.Builder;
import on.logistics.slackservice.domain.entity.Slack;

@Builder
public record ReadSlackMessageResponse(
    UUID id,
    String slackSendEmail,
    String slackReceiveEmail,
    UUID userSendId,
    UUID userReceiveId,
    String message
) {

    public static ReadSlackMessageResponse from(Slack slack) {
        return ReadSlackMessageResponse.builder()
            .id(slack.getId())
            .slackSendEmail(slack.getSlackSendEmail())
            .slackReceiveEmail(slack.getSlackReceiveEmail())
            .userSendId(slack.getUserSendId())
            .userReceiveId(slack.getUserReceiveId())
            .message(slack.getMessage())
            .build();
    }


}
