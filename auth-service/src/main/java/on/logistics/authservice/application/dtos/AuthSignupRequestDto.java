package on.logistics.authservice.application.dtos;

import on.logistics.authservice.domain.vo.Password;
import on.logistics.authservice.domain.vo.Username;
import on.logistics.authservice.enums.AuthRole;
import on.logistics.authservice.presentation.dtos.AuthSignupRequest;

public record AuthSignupRequestDto(
    Username username,
    Password password,
    String nickname,
    AuthRole role,
    String slackEmail
) {

    public static AuthSignupRequestDto from(AuthSignupRequest request) {
        return new AuthSignupRequestDto(
            new Username(request.username()),
            new Password(request.password()),
            request.nickname(),
            request.role(),
            request.slackEmail()
        );
    }
}
