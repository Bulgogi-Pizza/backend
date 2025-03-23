package on.logistics.hubtransitservice.presentation.dtos.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import on.logistics.hubtransitservice.application.dtos.request.InboundHubTransitRequestDto;

public record InboundHubTransitRequest(
    @NotNull(message = "배송 ID는 필수입니다.") UUID deliveryId,
    @NotNull(message = "현재 허브 ID는 필수입니다.") UUID currentHubId
) {

    public static InboundHubTransitRequestDto from(InboundHubTransitRequest request) {
        return InboundHubTransitRequestDto.builder()
            .deliveryId(request.deliveryId)
            .currentHubId(request.currentHubId)
            .build();
    }

}
