package on.logistics.hubtransitservice.presentation.dtos.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record UpdateHubTransitRequest(
    @NotNull(message = "배송 담당자 ID는 필수입니다.") UUID userId
) {

}
