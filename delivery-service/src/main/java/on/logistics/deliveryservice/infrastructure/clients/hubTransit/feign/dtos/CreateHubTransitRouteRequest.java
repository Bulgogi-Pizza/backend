package on.logistics.deliveryservice.infrastructure.clients.hubTransit.feign.dtos;

import java.util.UUID;

public record CreateHubTransitRouteRequest(UUID startHubId, UUID endHubId, UUID deliveryId) {

    public static CreateHubTransitRouteRequest of(UUID startHubId, UUID endHubId,
        UUID deliveryId) {
        return new CreateHubTransitRouteRequest(startHubId, endHubId, deliveryId);
    }

}
