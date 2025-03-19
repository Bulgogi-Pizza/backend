package on.logistics.userservice.presentation.dtos;

import java.util.UUID;
import on.logistics.userservice.domain.entity.User;

public record UserCreateResponse(
    UUID userId,
    String nickname,
    String slackEmail
) {

    public static UserCreateResponse from(User user) {
        return new UserCreateResponse(
            user.getId(),
            user.getNickname(),
            user.getSlackEmail()
        );
    }

}
