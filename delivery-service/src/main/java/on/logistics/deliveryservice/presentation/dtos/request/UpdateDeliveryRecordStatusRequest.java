package on.logistics.deliveryservice.presentation.dtos.request;

import on.logistics.deliveryservice.domain.enums.DeliveryRecordStatus;

public record UpdateDeliveryRecordStatusRequest(DeliveryRecordStatus deliveryRecordStatus) {

}