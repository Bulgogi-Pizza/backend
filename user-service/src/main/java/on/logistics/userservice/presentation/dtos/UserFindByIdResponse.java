package on.logistics.userservice.presentation.dtos;


import java.util.UUID;
import on.logistics.userservice.domain.entity.User;

public record UserFindByIdResponse(
    UUID userId,
    String nickname,
    String slackEmail
) {
    public static UserFindByIdResponse from(User user) {
        return new UserFindByIdResponse(
            user.getId(),
            user.getNickname(),
            user.getSlackEmail()
        );
    }

}
