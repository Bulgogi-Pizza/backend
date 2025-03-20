package on.logistics.deliveryservice.infrastructure.clients.hubTransit.feign.dtos;

import java.util.UUID;

public record CreateHubTransitRouteResponse(UUID transitId, UUID currentHubId,
                                            String currentHubName, UUID nextHubId,
                                            String nextHubName, UUID deliveryId,
                                            UUID deliveryManagerId) {

}
