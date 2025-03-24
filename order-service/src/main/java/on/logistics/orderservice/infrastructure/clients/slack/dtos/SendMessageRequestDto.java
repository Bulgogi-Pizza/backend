package on.logistics.orderservice.infrastructure.clients.slack.dtos;

import java.util.UUID;

public record SendMessageRequestDto(
    String slackSendEmail,
    String slackReceiveEmail,
    UUID userSendId,
    UUID userReceiveId,
    String message
) {

    public static SendMessageRequestDto of(
        UUID userId,
        String userSlackEmail,
        String slackEmail,
        UUID hubManagerId,
        String message
    ) {
        return new SendMessageRequestDto(
            userSlackEmail,
            slackEmail,
            userId,
            hubManagerId,
            message
        );
    }
}
