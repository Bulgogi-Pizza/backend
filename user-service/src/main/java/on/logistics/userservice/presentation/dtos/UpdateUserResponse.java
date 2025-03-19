package on.logistics.userservice.presentation.dtos;

import on.logistics.userservice.domain.entity.User;

public record UpdateUserResponse(
    String nickname,
    String slackEmail
) {
    public static UpdateUserResponse from(User user) {
        return new UpdateUserResponse(
            user.getNickname(),
            user.getSlackEmail()
        );
    }
}
