package on.logistics.userservice.presentation.dtos;

import java.util.UUID;
import on.logistics.userservice.domain.entity.User;

public record SearchUserResponse(
    UUID userId,
    String nickname,
    String slackEmail
) {
    public static SearchUserResponse from(User user) {
        return new SearchUserResponse(
            user.getId(),
            user.getNickname(),
            user.getSlackEmail()
        );
    }

}
