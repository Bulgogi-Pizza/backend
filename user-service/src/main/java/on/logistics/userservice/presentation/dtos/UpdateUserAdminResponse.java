package on.logistics.userservice.presentation.dtos;

import on.logistics.userservice.domain.entity.User;

public record UpdateUserAdminResponse(
    String nickname,
    String slackEmail
) {

    public static UpdateUserAdminResponse from(User user) {
        return new UpdateUserAdminResponse(
            user.getNickname(),
            user.getSlackEmail()
        );
    }
}
