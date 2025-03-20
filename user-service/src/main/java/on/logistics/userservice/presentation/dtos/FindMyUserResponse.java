package on.logistics.userservice.presentation.dtos;

import java.util.UUID;
import on.logistics.userservice.domain.entity.User;

public record FindMyUserResponse(
    UUID userId,
    String nickname,
    String slackEmail
) {
    public static FindMyUserResponse from(User user) {
        return new FindMyUserResponse(
            user.getId(),
            user.getNickname(),
            user.getSlackEmail()
        );
    }

}

