package on.logistics.deliveryservice.presentation.dtos.request;

import jakarta.validation.constraints.NotNull;
import on.logistics.deliveryservice.domain.enums.DeliveryRecordStatus;

public record UpdateDeliveryRecordStatusRequest(
    @NotNull(message = "deliveryRecordStatus 는 필수 입력 값입니다.") DeliveryRecordStatus deliveryRecordStatus) {

}