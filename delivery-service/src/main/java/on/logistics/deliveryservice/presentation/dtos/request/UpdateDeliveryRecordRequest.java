package on.logistics.deliveryservice.presentation.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateDeliveryRecordRequest(
    @NotBlank(message = "actualDistance 는 필수 입력 값입니다.") Long actualDistance,
    @NotBlank(message = "actualDistance 는 필수 입력 값입니다.") Long actualDuration) {

}
