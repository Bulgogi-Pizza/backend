package on.logistics.deliveryservice.presentation.dtos.request;

import jakarta.validation.constraints.NotNull;

public record UpdateDeliveryRecordRequest(
    @NotNull(message = "actualDistance 는 필수 입력 값입니다.") Long actualDistance,
    @NotNull(message = "actualDistance 는 필수 입력 값입니다.") Long actualDuration) {

}
