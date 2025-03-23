package on.logistics.deliveryservice.infrastructure.clients.hub.feign.dtos;

import java.util.UUID;

public record HubManagerBooleanRequest(UUID userId, UUID hubId) {

    public static HubManagerBooleanRequest of(UUID userId, UUID hubId) {
        return new HubManagerBooleanRequest(userId, hubId);
    }
}
