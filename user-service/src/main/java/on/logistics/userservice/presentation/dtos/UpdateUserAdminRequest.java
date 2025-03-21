package on.logistics.userservice.presentation.dtos;

public record UpdateUserAdminRequest(
    String nickname,
    String slackEmail
) {

}
