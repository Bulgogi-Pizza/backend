package on.logistics.authservice.global.domain;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import on.logistics.authservice.enums.AuthRole;
import on.logistics.authservice.infrastructure.feign.dtos.UserFindByIdResponse;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class Passport {

    private UUID userId;
    private String username;
    private AuthRole role;
    private String nickname;
    private String slackEmail;

    public static Passport from(UserFindByIdResponse dto, String username, String role) {
        return Passport.builder()
            .userId(dto.userId())
            .username(username)
            .role(AuthRole.valueOf(role))
            .nickname(dto.nickname())
            .slackEmail(dto.slackEmail())
            .build();
    }
}
