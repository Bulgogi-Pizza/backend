package on.logistics.hubtransitservice.domain.dtos;

import java.util.UUID;
import lombok.Builder;
import on.logistics.hubtransitservice.domain.enums.DeliveryType;

@Builder
public record CreateHubTransitDto(
    UUID deliveryId,
    UUID deliveryRecordId,
    UUID currentHubId,
    String currentHubName,
    UUID nextHubId,
    String nextHubName,
    DeliveryType nextDeliveryType,
    UUID userId,
    String routeSnapshot
) {

}
