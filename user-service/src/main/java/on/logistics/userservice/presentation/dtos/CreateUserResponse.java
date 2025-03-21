package on.logistics.userservice.presentation.dtos;

import java.util.UUID;
import on.logistics.userservice.domain.entity.User;

public record CreateUserResponse(
    UUID userId,
    String nickname,
    String slackEmail
) {

    public static CreateUserResponse from(User user) {
        return new CreateUserResponse(
            user.getId(),
            user.getNickname(),
            user.getSlackEmail()
        );
    }

}
