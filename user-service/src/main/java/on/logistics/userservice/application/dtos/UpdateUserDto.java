package on.logistics.userservice.application.dtos;

import on.logistics.userservice.domain.entity.vo.SlackEmail;
import on.logistics.userservice.presentation.dtos.UpdateUserRequest;

// TODO: nickname 유효성 검사 필요
public record UpdateUserDto(
    String nickname,
    SlackEmail slackEmail
) {

    public static UpdateUserDto from(UpdateUserRequest request) {
        return new UpdateUserDto(
            request.nickname(),
            new SlackEmail(request.slackEmail())
        );
    }
}
