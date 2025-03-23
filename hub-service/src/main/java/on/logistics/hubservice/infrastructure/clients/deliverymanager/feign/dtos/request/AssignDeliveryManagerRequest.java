package on.logistics.hubservice.infrastructure.clients.deliverymanager.feign.dtos.request;

import lombok.Builder;
import on.logistics.hubservice.application.dtos.request.AssignDeliveryManagerRequestDto;

@Builder
public record AssignDeliveryManagerRequest(
    String deliveryId,
    String hubId,
    String deliveryType
) {

    public static AssignDeliveryManagerRequest of(AssignDeliveryManagerRequestDto dto) {
        return AssignDeliveryManagerRequest.builder()
            .deliveryId(dto.deliveryId())
            .hubId(dto.hubId())
            .deliveryType(dto.deliveryType())
            .build();
    }
}
