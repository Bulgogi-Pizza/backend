package on.logistics.userservice.presentation.dtos;

public record CreateUserRequest(
    String nickname,
    String slackEmail
) {

}
