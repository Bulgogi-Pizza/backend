package on.logistics.auth_service.application.dtos;

import on.logistics.auth_service.presentation.dtos.AuthSignupRequest;

public record AuthSignupRequestDto (
    String username,
    String password,
    String nickname,
    String slackEmail
){

    public static AuthSignupRequestDto from(AuthSignupRequest request) {
        return new AuthSignupRequestDto(
            request.username(),
            request.password(),
            request.nickname(),
            request.slackEmail()
        );
    }
}
