package on.logistics.hubtransitservice.infrastructure.clients.deliverymanager.feign.dtos;

import java.util.UUID;
import lombok.Builder;

@Builder
public record AssignDeliveryManagerRequest(
    UUID deliveryId,
    UUID hubId,
    String deliveryType
) {

}
