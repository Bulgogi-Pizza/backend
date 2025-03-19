package on.logistics.userservice.global.domain;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import on.logistics.userservice.global.enums.AuthRole;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class Passport {

    private UUID userId;
    private String username;
    private AuthRole role;
    private String nickname;
    private String slackEmail;

}
