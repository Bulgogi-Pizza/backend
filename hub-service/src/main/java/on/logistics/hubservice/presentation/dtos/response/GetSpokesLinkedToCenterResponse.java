package on.logistics.hubservice.presentation.dtos.response;

import com.querydsl.core.annotations.QueryProjection;
import java.math.BigDecimal;
import java.util.UUID;

public record GetSpokesLinkedToCenterResponse(
    UUID id,
    String hubName,
    String hubType,
    String address,
    BigDecimal latitude,
    BigDecimal longitude
) {

    @QueryProjection
    public GetSpokesLinkedToCenterResponse {

    }

}
