package on.logistics.hubtransitservice.presentation.dtos.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import on.logistics.hubtransitservice.application.dtos.request.CreateHubTransitRequestDto;

public record CreateHubTransitRequest(
    @NotNull(message = "배송 ID는 필수입니다.") UUID deliveryId,
    @NotNull(message = "시작 허브 ID는 필수입니다.") UUID startHubId,
    @NotNull(message = "마지막 허브 ID는 필수입니다.") UUID endHubId
) {

    public static CreateHubTransitRequestDto from(CreateHubTransitRequest request) {
        return CreateHubTransitRequestDto.builder()
            .deliveryId(request.deliveryId)
            .startHubId(request.startHubId)
            .endHubId(request.endHubId)
            .build();
    }

}
