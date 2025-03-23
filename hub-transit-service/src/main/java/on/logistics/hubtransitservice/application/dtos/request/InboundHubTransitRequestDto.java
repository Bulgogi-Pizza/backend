package on.logistics.hubtransitservice.application.dtos.request;

import java.util.UUID;
import lombok.Builder;

@Builder
public record InboundHubTransitRequestDto(
    UUID deliveryId,
    UUID currentHubId
) {

}
