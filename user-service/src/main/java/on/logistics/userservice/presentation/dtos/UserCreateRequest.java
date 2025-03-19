package on.logistics.userservice.presentation.dtos;

public record UserCreateRequest(
    String nickname,
    String slackEmail
) {

}
