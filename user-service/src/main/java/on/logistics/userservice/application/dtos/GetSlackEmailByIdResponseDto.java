package on.logistics.userservice.application.dtos;

import on.logistics.userservice.domain.entity.User;

public record GetSlackEmailByIdResponseDto(
    String slackEmail
) {

    public static GetSlackEmailByIdResponseDto from(User user) {
        return new GetSlackEmailByIdResponseDto(
            user.getSlackEmail()
        );
    }
}
