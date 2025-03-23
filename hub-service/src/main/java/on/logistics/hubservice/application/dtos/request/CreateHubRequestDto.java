package on.logistics.hubservice.application.dtos.request;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import on.logistics.hubservice.domain.entity.HubType;
import on.logistics.hubservice.presentation.dtos.request.CreateHubRequest;

@Builder
public record CreateHubRequestDto(
    String hubName,
    HubType hubType,
    String hubAddress,
    HttpServletRequest passportRequest
) {

    public static CreateHubRequestDto of(CreateHubRequest request,
        HttpServletRequest passportRequest) {
        return CreateHubRequestDto.builder()
            .hubName(request.hubName())
            .hubType(HubType.valueOf(request.hubType()))
            .hubAddress(request.hubAddress())
            .passportRequest(passportRequest)
            .build();
    }
}
