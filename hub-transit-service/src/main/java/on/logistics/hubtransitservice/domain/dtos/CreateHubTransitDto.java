package on.logistics.hubtransitservice.domain.dtos;

import java.util.UUID;
import on.logistics.hubtransitservice.application.dtos.create.CreateHubTransitRequestDto;

public record CreateHubTransitDto(
    UUID startHubId,
    String startHubName,
    UUID endHubId,
    String endHubName,
    UUID deliveryId,
    UUID deliveryManagerId
) {

    public static CreateHubTransitDto of(CreateHubTransitRequestDto dto) {
        return new CreateHubTransitDto(
            dto.startHubId(),
            dto.startHubName(),
            dto.endHubId(),
            dto.endHubName(),
            dto.deliveryId(),
            dto.deliveryManagerId()
        );
    }

}
