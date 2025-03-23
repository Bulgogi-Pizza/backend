package on.logistics.hubservice.infrastructure.clients.hubtransit.feign.dtos.request;

import java.util.UUID;
import lombok.Builder;

@Builder
public record NextHubTransitRequest(
    UUID deliveryId,
    UUID currentHubId
) {

    public static NextHubTransitRequest of(UUID deliveryId, UUID currentHubId) {
        return NextHubTransitRequest.builder()
            .deliveryId(deliveryId)
            .currentHubId(currentHubId)
            .build();
    }
}
