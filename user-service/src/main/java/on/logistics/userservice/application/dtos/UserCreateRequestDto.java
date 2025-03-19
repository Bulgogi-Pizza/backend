package on.logistics.userservice.application.dtos;

import on.logistics.userservice.domain.entity.vo.SlackEmail;
import on.logistics.userservice.presentation.dtos.UserCreateRequest;

public record UserCreateRequestDto(
    String nickname,
    SlackEmail slackEmail
) {

    public static UserCreateRequestDto from(UserCreateRequest request) {
        return new UserCreateRequestDto(
            request.nickname(),
            new SlackEmail(request.slackEmail())
        );
    }
}
