package on.logistics.userservice.application.dtos;

import on.logistics.userservice.domain.entity.vo.SlackEmail;
import on.logistics.userservice.presentation.dtos.CreateUserRequest;

public record CreateUserDto(
    String nickname,
    SlackEmail slackEmail
) {

    public static CreateUserDto from(CreateUserRequest request) {
        return new CreateUserDto(
            request.nickname(),
            new SlackEmail(request.slackEmail())
        );
    }
}
