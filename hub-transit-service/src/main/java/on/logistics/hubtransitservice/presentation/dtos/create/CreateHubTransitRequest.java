package on.logistics.hubtransitservice.presentation.dtos.create;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import on.logistics.hubtransitservice.application.dtos.create.CreateHubTransitRequestDto;

public record CreateHubTransitRequest(
    @NotNull(message = "출발 허브 ID는 필수입니다.") UUID startHubId,
    @NotNull(message = "도착 허브 ID는 필수입니다.") UUID endHubId,
    @NotNull(message = "배송 ID는 필수입니다.") UUID deliveryId
) {

    public static CreateHubTransitRequestDto from(CreateHubTransitRequest request) {
        return CreateHubTransitRequestDto.of(request.startHubId, request.endHubId,
            request.deliveryId, null, null, null);
    }

}
