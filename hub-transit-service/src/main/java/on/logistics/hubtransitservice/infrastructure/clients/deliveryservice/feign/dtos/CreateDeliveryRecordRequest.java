package on.logistics.hubtransitservice.infrastructure.clients.deliveryservice.feign.dtos;

import java.util.UUID;
import lombok.Builder;

@Builder
public record CreateDeliveryRecordRequest(
    UUID deliveryId,
    UUID deliveryRecordStartHubId,
    UUID deliveryRecordEndHubId,
    UUID userId
) {

}
