package on.logistics.userservice.presentation.dtos;


import java.util.UUID;
import on.logistics.userservice.domain.entity.User;

public record FindByIdUserResponse(
    UUID userId,
    String nickname,
    String slackEmail
) {
    public static FindByIdUserResponse from(User user) {
        return new FindByIdUserResponse(
            user.getId(),
            user.getNickname(),
            user.getSlackEmail()
        );
    }

}
