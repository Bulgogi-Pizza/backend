package on.logistics.authservice.presentation.dtos;

import on.logistics.authservice.enums.AuthRole;

public record AuthSignupRequest(
    String username,
    String password,
    String nickname,
    AuthRole role,
    String slackEmail
) {

}
