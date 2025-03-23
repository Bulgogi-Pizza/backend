package on.logistics.hubservice.application.dtos.request;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import lombok.Builder;
import on.logistics.hubservice.domain.entity.HubType;
import on.logistics.hubservice.presentation.dtos.request.UpdateHubRequest;

@Builder
public record UpdateHubRequestDto(
    UUID id,
    String hubName,
    HubType hubType,
    String hubAddress,
    HttpServletRequest passportRequest
) {

    public static UpdateHubRequestDto of(UUID id, UpdateHubRequest request,
        HttpServletRequest passportRequest) {
        return UpdateHubRequestDto.builder()
            .id(id)
            .hubName(request.hubName())
            .hubType(HubType.valueOf(request.hubType()))
            .hubAddress(request.hubAddress())
            .passportRequest(passportRequest)
            .build();
    }

}
