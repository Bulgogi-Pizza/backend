package on.logistics.orderservice.infrastructure.clients.hub.feign.dtos;

import java.util.UUID;

public record ValidateHubManagerRequest(
    UUID companyId,
    UUID hubId
) {

    public static ValidateHubManagerRequest of(UUID companyId, UUID hubId) {
        return new ValidateHubManagerRequest(companyId, hubId);
    }
}
