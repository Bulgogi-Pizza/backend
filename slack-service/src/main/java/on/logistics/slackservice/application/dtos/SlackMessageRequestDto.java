package on.logistics.slackservice.application.dtos;

import java.util.UUID;
import lombok.Builder;
import on.logistics.slackservice.domain.dtos.CreateSlackMessageDto;

@Builder
public record SlackMessageRequestDto(
    String slackSendEmail,
    String slackReceiveEmail,
    UUID userSendId,
    UUID userReceiveId,
    String message
) {

    public static CreateSlackMessageDto from(SlackMessageRequestDto dto) {
        return CreateSlackMessageDto.builder()
            .slackSendEmail(dto.slackSendEmail())
            .slackReceiveEmail(dto.slackReceiveEmail())
            .userSendId(dto.userSendId())
            .userReceiveId(dto.userReceiveId())
            .message(dto.message())
            .build();
    }

}
