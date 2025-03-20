package on.logistics.hubtransitservice.application.dtos.read;

import java.util.UUID;

public record NextHubTransitResponseDto(
    UUID transitId,
    UUID currentHubId,
    UUID nextHubId,
    String nextHubName,
    UUID deliveryManagerId
) {

    public static NextHubTransitResponseDto of(
        UUID transitId,
        UUID currentHubId,
        UUID nextHubId,
        String nextHubName,
        UUID deliveryManagerId
    ) {
        return new NextHubTransitResponseDto(
            transitId, currentHubId, nextHubId, nextHubName, deliveryManagerId);
    }

}
