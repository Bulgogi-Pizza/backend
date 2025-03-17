package on.logistics.auth_service.presentation.dtos;

import lombok.Builder;
import lombok.Getter;
import on.logistics.auth_service.application.dtos.AuthSignupResponseDto;

@Getter
@Builder
public record AuthSignupResponse(
    String username,
    String nickname
) {

    public static AuthSignupResponse from(AuthSignupResponseDto dto) {
        return new AuthSignupResponse(
            dto.username(),
            dto.nickname()
        );
    }
}
