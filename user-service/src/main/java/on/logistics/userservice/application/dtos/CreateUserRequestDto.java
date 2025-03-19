package on.logistics.userservice.application.dtos;

import on.logistics.userservice.domain.entity.vo.SlackEmail;
import on.logistics.userservice.presentation.dtos.CreateUserRequest;

public record CreateUserRequestDto(
    String nickname,
    SlackEmail slackEmail
) {

    public static CreateUserRequestDto from(CreateUserRequest request) {
        return new CreateUserRequestDto(
            request.nickname(),
            new SlackEmail(request.slackEmail())
        );
    }
}
