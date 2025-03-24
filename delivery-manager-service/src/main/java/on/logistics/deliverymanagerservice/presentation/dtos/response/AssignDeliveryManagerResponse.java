package on.logistics.deliverymanagerservice.presentation.dtos.response;

import java.util.UUID;
import lombok.Builder;

@Builder
public record AssignDeliveryManagerResponse(
    UUID userId
) {

    public static AssignDeliveryManagerResponse of(UUID userId) {
        return AssignDeliveryManagerResponse.builder()
            .userId(userId)
            .build();
    }
}
