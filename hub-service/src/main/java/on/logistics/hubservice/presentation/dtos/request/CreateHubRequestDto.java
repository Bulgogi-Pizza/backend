package on.logistics.hubservice.presentation.dtos.request;

import lombok.Builder;
import on.logistics.hubservice.domain.entity.HubType;

@Builder
public record CreateHubRequestDto(
    String hubName,
    HubType hubType,
    String hubAddress
) {

    public static CreateHubRequestDto of(CreateHubRequest request) {
        return CreateHubRequestDto.builder()
            .hubName(request.hubName())
            .hubType(HubType.valueOf(request.hubType()))
            .hubAddress(request.hubAddress())
            .build();
    }
}
