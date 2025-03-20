package on.logistics.hubtransitservice.presentation.dtos.create;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import on.logistics.hubtransitservice.application.dtos.create.CreateNextHubTransitRequestDto;

public record CreateNextHubTransitRequest(
    @NotNull(message = "transit ID는 필수입니다.") UUID transitId,
    @NotNull(message = "현재 허브 ID는 필수입니다.") UUID currentHubId
) {

    public static CreateNextHubTransitRequestDto from(CreateNextHubTransitRequest request) {
        return new CreateNextHubTransitRequestDto(request.transitId(), request.currentHubId());
    }

}
