package on.logistics.userservice.application.dtos;

import java.util.UUID;
import on.logistics.userservice.domain.entity.vo.SlackEmail;
import on.logistics.userservice.presentation.dtos.UpdateUserAdminRequest;

public record UpdateUserAdminDto(
    UUID userId,
    String nickname,
    SlackEmail slackEmail
) {

    public static UpdateUserAdminDto from(
        UUID userId,
        UpdateUserAdminRequest updateUserAdminRequest) {
        return new UpdateUserAdminDto(
            userId,
            updateUserAdminRequest.nickname(),
            new SlackEmail(updateUserAdminRequest.slackEmail())
        );
    }
}
