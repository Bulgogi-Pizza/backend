package on.logistics.hubtransitservice.infrastructure.clients.deliverymanager.feign.dtos;

import java.util.UUID;

public record AssignDeliveryManagerRequest(
    UUID deliveryId,
    UUID nextHubId,
    String nextDestinationType
) {

    public static AssignDeliveryManagerRequest forFinalDestination(UUID deliveryId) {
        return new AssignDeliveryManagerRequest(
            deliveryId,
            UUID.fromString("00000000-0000-0000-0000-00000000"),
            "COMPANY"
        );
    }

}
