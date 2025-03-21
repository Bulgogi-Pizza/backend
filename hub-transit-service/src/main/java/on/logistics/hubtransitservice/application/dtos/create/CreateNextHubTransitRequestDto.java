package on.logistics.hubtransitservice.application.dtos.create;

import java.util.UUID;

public record CreateNextHubTransitRequestDto(
    UUID transitId,
    UUID currentHubId

) {

}
