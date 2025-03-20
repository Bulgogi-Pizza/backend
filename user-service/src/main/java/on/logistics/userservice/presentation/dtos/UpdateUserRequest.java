package on.logistics.userservice.presentation.dtos;

public record UpdateUserRequest (
    String nickname,
    String slackEmail
) {

}
