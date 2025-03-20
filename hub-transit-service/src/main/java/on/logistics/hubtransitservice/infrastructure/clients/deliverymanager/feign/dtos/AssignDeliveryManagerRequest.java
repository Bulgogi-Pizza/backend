package on.logistics.hubtransitservice.infrastructure.clients.deliverymanager.feign.dtos;

import java.util.UUID;

public record AssignDeliveryManagerRequest(
    UUID deliveryId,
    UUID nextHubId,
    String nextDestinationType
) {

}
