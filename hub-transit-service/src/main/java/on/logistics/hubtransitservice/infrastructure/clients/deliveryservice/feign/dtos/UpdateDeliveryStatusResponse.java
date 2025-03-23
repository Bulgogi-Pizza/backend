package on.logistics.hubtransitservice.infrastructure.clients.deliveryservice.feign.dtos;

import java.util.UUID;

public record UpdateDeliveryStatusResponse(
    UUID deliveryRecordId
) {

}
