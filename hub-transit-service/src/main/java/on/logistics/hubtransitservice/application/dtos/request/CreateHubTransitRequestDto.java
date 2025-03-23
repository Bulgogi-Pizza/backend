package on.logistics.hubtransitservice.application.dtos.request;

import java.util.UUID;
import lombok.Builder;

@Builder
public record CreateHubTransitRequestDto(
    UUID deliveryId,
    UUID startHubId,
    UUID endHubId
) {

}
