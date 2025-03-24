package on.logistics.authservice.application.dtos;

import on.logistics.authservice.presentation.dtos.AuthLoginRequest;

public record AuthLoginRequestDto(
    String username,
    String password
) {
    public static AuthLoginRequestDto from(AuthLoginRequest authLoginRequest) {
        return new AuthLoginRequestDto(
            authLoginRequest.username(),
            authLoginRequest.password()
        );
    }
}
