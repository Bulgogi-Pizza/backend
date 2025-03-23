package on.logistics.deliverymanagerservice.application.dtos;

import java.util.UUID;
import lombok.Builder;
import on.logistics.deliverymanagerservice.domain.entity.DeliveryType;
import on.logistics.deliverymanagerservice.presentation.dtos.request.AssignDeliveryManagerRequest;

@Builder
public record AssignDeliveryManagerRequestDto(
    UUID deliveryId,
    UUID hubId,
    DeliveryType type
) {
    public static AssignDeliveryManagerRequestDto of(AssignDeliveryManagerRequest request) {
        return AssignDeliveryManagerRequestDto.builder()
            .deliveryId(UUID.fromString(request.deliveryId()))
            .hubId(java.util.UUID.fromString(request.hubId()))
            .type(DeliveryType.valueOf(request.deliveryType()))
            .build();
    }
}
