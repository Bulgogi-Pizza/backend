package on.logistics.deliverymanagerservice.infrastructure.clients.user.feign.dtos.response;

import java.util.UUID;

public record GetUserInfoResponse(
    UUID userId,
    String nickname,
    String slackEmail
) {

}
