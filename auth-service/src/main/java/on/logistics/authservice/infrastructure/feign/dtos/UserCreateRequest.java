package on.logistics.authservice.infrastructure.feign.dtos;

import on.logistics.authservice.application.dtos.AuthSignupRequestDto;

public record UserCreateRequest(
    String nickname,
    String slackEmail
) {

    public static UserCreateRequest from(AuthSignupRequestDto dto) {
        return new UserCreateRequest(
            dto.nickname(),
            dto.slackEmail()
        );
    }

}
