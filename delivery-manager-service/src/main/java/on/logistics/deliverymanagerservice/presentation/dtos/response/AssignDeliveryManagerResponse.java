package on.logistics.deliverymanagerservice.presentation.dtos.response;

import java.util.UUID;
import lombok.Builder;

@Builder
public record AssignDeliveryManagerResponse(
    UUID deliveryManagerId
) {
    public static AssignDeliveryManagerResponse of(UUID deliveryManagerId) {
        return AssignDeliveryManagerResponse.builder()
            .deliveryManagerId(deliveryManagerId)
            .build();
    }
}
