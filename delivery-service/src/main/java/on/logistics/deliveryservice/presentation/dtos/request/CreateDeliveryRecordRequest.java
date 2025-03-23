package on.logistics.deliveryservice.presentation.dtos.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record CreateDeliveryRecordRequest(
    @NotNull(message = "deliveryID는 필수 입력 값입니다.") UUID deliveryId,
    @NotNull(message = "deliveryRecordStartHubId는 필수 입력 값입니다.") UUID deliveryRecordStartHubId,
    @NotNull(message = "deliveryRecordEndHubId는 필수 입력 값입니다.") UUID deliveryRecordEndHubId,
    @NotNull(message = "deliveryManagerId는 필수 입력 값입니다.") UUID userId) {

}