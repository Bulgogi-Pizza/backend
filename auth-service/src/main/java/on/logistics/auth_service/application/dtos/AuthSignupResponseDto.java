package on.logistics.auth_service.application.dtos;

import lombok.Builder;
import lombok.Getter;
import on.logistics.auth_service.domain.entity.Auth;

@Getter
@Builder
public record AuthSignupResponseDto(
    String username,
    String nickname
) {

    public static AuthSignupResponseDto from(Auth auth, UserSignupResponseDto dto) {
        return new AuthSignupResponseDto(
            auth.getUsername(),
            dto.nickname()
        );
    }
}
