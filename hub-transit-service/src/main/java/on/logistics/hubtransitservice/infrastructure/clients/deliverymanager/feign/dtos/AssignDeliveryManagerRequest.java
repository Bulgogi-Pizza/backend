package on.logistics.hubtransitservice.infrastructure.clients.deliverymanager.feign.dtos;

import java.util.UUID;
import lombok.Builder;
import on.logistics.hubtransitservice.domain.enums.DeliveryType;

@Builder
public record AssignDeliveryManagerRequest(
    UUID deliveryId,
    UUID hubId,
    DeliveryType deliveryType
) {

}
