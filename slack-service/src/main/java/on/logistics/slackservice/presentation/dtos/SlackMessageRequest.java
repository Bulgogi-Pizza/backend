package on.logistics.slackservice.presentation.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import on.logistics.slackservice.application.dtos.SlackMessageRequestDto;

public record SlackMessageRequest(
    @Email @NotNull(message = "slack send email은 필수입니다.") String slackSendEmail,
    @Email @NotNull(message = "slack receive email은 필수입니다.") String slackReceiveEmail,
    @NotNull(message = "user send id는 필수입니다.") UUID userSendId,
    @NotNull(message = "user receive id는 필수입니다.") UUID userReceiveId,
    @NotBlank(message = "message는 필수입니다.") String message
) {

    public static SlackMessageRequestDto from(SlackMessageRequest request
    ) {
        return SlackMessageRequestDto.builder()
            .slackSendEmail(request.slackSendEmail)
            .slackReceiveEmail(request.slackReceiveEmail)
            .userSendId(request.userSendId)
            .userReceiveId(request.userReceiveId)
            .message(request.message)
            .build();
    }

}
