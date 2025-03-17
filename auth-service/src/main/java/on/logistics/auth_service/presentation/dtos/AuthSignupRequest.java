package on.logistics.auth_service.presentation.dtos;

public record AuthSignupRequest(
    String username,
    String password,
    String nickname,
    String slackEmail
) {

}
