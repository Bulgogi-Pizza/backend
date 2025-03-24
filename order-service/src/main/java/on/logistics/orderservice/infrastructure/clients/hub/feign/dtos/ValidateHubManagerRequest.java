package on.logistics.orderservice.infrastructure.clients.hub.feign.dtos;

import java.util.UUID;

public record ValidateHubManagerRequest(
    UUID userId,
    UUID hubId
) {

    public static ValidateHubManagerRequest of(UUID userId, UUID hubId) {
        return new ValidateHubManagerRequest(userId, hubId);
    }
}
