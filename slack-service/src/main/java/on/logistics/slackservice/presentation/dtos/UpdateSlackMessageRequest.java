package on.logistics.slackservice.presentation.dtos;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
import on.logistics.slackservice.application.dtos.UpdateSlackMessageRequestDto;

public record UpdateSlackMessageRequest(
    @NotBlank(message = "메시지는 필수입니다.") String message
) {

    public static UpdateSlackMessageRequestDto of(UUID id, UpdateSlackMessageRequest request) {
        return UpdateSlackMessageRequestDto.builder()
            .id(id)
            .message(request.message())
            .build();
    }

}
