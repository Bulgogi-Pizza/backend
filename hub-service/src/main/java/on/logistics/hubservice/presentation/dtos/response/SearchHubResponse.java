package on.logistics.hubservice.presentation.dtos.response;

import com.querydsl.core.annotations.QueryProjection;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;
import on.logistics.hubservice.domain.entity.Hub;

@Builder
public record SearchHubResponse(
    UUID id,
    String hubName,
    String hubType,
    String address,
    BigDecimal latitude,
    BigDecimal longitude
) {

    @QueryProjection
    public SearchHubResponse {

    }

    public static SearchHubResponse of(Hub hub) {
        return SearchHubResponse.builder()
            .id(hub.getId())
            .hubName(hub.getName().getValue())
            .hubType(String.valueOf(hub.getType()))
            .address(hub.getAddress().getValue())
            .latitude(hub.getLatitude())
            .longitude(hub.getLongitude())
            .build();
    }
}
