package on.logistics.authservice.application.dtos;

import lombok.Builder;
import on.logistics.authservice.domain.entity.Auth;
import on.logistics.authservice.domain.vo.Username;
import on.logistics.authservice.infrastructure.feign.dtos.UserCreateResponse;

@Builder
public record AuthSignupResponseDto(
    Username username,
    String nickname
) {

    public static AuthSignupResponseDto from(Auth auth, UserCreateResponse dto) {
        return new AuthSignupResponseDto(
            auth.getUsername(),
            dto.nickname()
        );
    }
}
