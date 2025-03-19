package on.logistics.deliveryservice.application.service;

import on.logistics.deliveryservice.application.dtos.request.CreateDeliveryRecordRequestDto;
import on.logistics.deliveryservice.presentation.dtos.response.CreateDeliveryRecordResponse;

public interface DeliveryRecordService {

    CreateDeliveryRecordResponse createDeliveryRecord(CreateDeliveryRecordRequestDto requestDto);
}
