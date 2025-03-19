package on.logistics.deliveryservice.application.service;

import java.util.UUID;
import on.logistics.deliveryservice.application.dtos.request.CreateDeliveryRecordRequestDto;
import on.logistics.deliveryservice.application.dtos.request.UpdateDeliveryRecordRequestDto;
import on.logistics.deliveryservice.presentation.dtos.response.CreateDeliveryRecordResponse;
import on.logistics.deliveryservice.presentation.dtos.response.UpdateDeliveryRecordResponse;

public interface DeliveryRecordService {

    CreateDeliveryRecordResponse createDeliveryRecord(CreateDeliveryRecordRequestDto requestDto);

    UpdateDeliveryRecordResponse updateActualDeliveryRecord(
        UpdateDeliveryRecordRequestDto requestDto);

    void deleteDeliveryRecord(UUID id);
}
