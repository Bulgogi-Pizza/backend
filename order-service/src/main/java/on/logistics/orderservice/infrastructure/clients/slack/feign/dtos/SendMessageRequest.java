package on.logistics.orderservice.infrastructure.clients.slack.feign.dtos;

import java.util.UUID;
import on.logistics.orderservice.infrastructure.clients.slack.dtos.SendMessageRequestDto;

public record SendMessageRequest(
    String slackSendEmail,
    String slackReceiveEmail,
    UUID userSendId,
    UUID userReceiveId,
    String message
) {

    public static SendMessageRequest from(SendMessageRequestDto requestDto) {
        return new SendMessageRequest(
            requestDto.slackSendEmail(),
            requestDto.slackReceiveEmail(),
            requestDto.userSendId(),
            requestDto.userReceiveId(),
            requestDto.message()
        );
    }
}
