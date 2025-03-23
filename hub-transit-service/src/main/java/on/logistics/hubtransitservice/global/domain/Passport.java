package on.logistics.hubtransitservice.global.domain;

import java.util.UUID;
import lombok.Getter;

@Getter
public class Passport {

    private UUID userId;
    private String username;
    private String role;
    private String nickname;
    private String slackEmail;

}