package on.logistics.hubtransitservice.application.dtos.read;

import java.util.UUID;

public record NextHubTransitRequestDto(
    UUID transitId,
    UUID currentHubId
) {

    public static NextHubTransitRequestDto of(UUID transitId, UUID currentHubId) {
        return new NextHubTransitRequestDto(
            transitId, currentHubId
        );
    }

}
