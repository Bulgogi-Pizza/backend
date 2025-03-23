package on.logistics.hubservice.application.dtos.request;

import java.util.UUID;
import lombok.Builder;

@Builder
public record AssignDeliveryManagerRequestDto(
    String deliveryId,
    String hubId,
    String deliveryType
) {

    public static AssignDeliveryManagerRequestDto of(UUID deliveryId, UUID hubId,
        String deliveryType) {
        return AssignDeliveryManagerRequestDto.builder()
            .deliveryId(String.valueOf(deliveryId))
            .hubId(String.valueOf(hubId))
            .deliveryType(deliveryType)
            .build();
    }

}
