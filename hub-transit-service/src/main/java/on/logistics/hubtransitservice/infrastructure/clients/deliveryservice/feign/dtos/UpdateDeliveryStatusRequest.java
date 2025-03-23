package on.logistics.hubtransitservice.infrastructure.clients.deliveryservice.feign.dtos;

import lombok.Builder;

@Builder
public record UpdateDeliveryStatusRequest(
    String deliveryRecordStatus
) {

}
