package on.logistics.deliverymanagerservice.presentation.dtos.response;

import com.querydsl.core.annotations.QueryProjection;
import java.util.UUID;

public record SearchDeliveryManagerResponse(
    UUID deliveryManagerId,
    String nickname,
    String hubName,
    String slackEmail,
    String deliveryType,
    int sequence

) {

    @QueryProjection
    public SearchDeliveryManagerResponse {

    }
}
