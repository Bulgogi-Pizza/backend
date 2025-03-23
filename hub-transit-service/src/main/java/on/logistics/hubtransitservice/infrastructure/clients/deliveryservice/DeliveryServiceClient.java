package on.logistics.hubtransitservice.infrastructure.clients.deliveryservice;

import java.util.UUID;
import on.logistics.hubtransitservice.infrastructure.clients.deliveryservice.feign.dtos.CreateDeliveryRecordRequest;
import on.logistics.hubtransitservice.infrastructure.clients.deliveryservice.feign.dtos.CreateDeliveryRecordResponse;
import on.logistics.hubtransitservice.infrastructure.clients.deliveryservice.feign.dtos.UpdateDeliveryStatusRequest;
import on.logistics.hubtransitservice.infrastructure.clients.deliveryservice.feign.dtos.UpdateDeliveryStatusResponse;

public interface DeliveryServiceClient {

    CreateDeliveryRecordResponse createDeliveryRecord(CreateDeliveryRecordRequest request);

    UpdateDeliveryStatusResponse updateDeliveryRecordStatus(UUID id,
        UpdateDeliveryStatusRequest request);

}
