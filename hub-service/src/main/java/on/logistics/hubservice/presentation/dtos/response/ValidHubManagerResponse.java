package on.logistics.hubservice.presentation.dtos.response;

import lombok.Builder;

@Builder
public record ValidHubManagerResponse(
    boolean isExist
) {

    public static ValidHubManagerResponse of(boolean isExist) {
        return ValidHubManagerResponse.builder()
            .isExist(isExist)
            .build();
    }
}
