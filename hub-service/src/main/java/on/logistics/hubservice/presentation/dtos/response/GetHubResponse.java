package on.logistics.hubservice.presentation.dtos.response;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;
import on.logistics.hubservice.domain.entity.Hub;

@Builder
public record GetHubResponse(
    UUID id,
    String hubName,
    String hubType,
    String address,
    BigDecimal latitude,
    BigDecimal longitude
) {

    public static GetHubResponse of(Hub hub) {
        return GetHubResponse.builder()
            .id(hub.getId())
            .hubName(hub.getName().getValue())
            .hubType(String.valueOf(hub.getType()))
            .address(hub.getAddress().getValue())
            .latitude(hub.getLatitude())
            .longitude(hub.getLongitude())
            .build();
    }
}
