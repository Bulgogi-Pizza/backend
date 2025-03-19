package on.logistics.authservice.infrastructure.feign.dtos;

import java.util.UUID;

public record UserFindByIdResponse(
    UUID userId,
    String nickname,
    String slackEmail
) {


}
