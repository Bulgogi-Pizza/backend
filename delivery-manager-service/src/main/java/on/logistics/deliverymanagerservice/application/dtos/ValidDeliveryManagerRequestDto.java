package on.logistics.deliverymanagerservice.application.dtos;

import java.util.UUID;
import lombok.Builder;
import on.logistics.deliverymanagerservice.presentation.dtos.request.ValidDeliveryManagerRequest;

@Builder
public record ValidDeliveryManagerRequestDto(
    UUID deliveryManager,
    UUID userId
) {

    public static ValidDeliveryManagerRequestDto of(ValidDeliveryManagerRequest request) {
        return ValidDeliveryManagerRequestDto.builder()
            .deliveryManager(UUID.fromString(request.deliveryManagerId()))
            .userId(UUID.fromString(request.userId()))
            .build();
    }
}
