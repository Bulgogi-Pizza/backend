package on.logistics.hubtransitservice.infrastructure.clients.deliveryservice;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import on.logistics.hubtransitservice.infrastructure.clients.deliveryservice.feign.dtos.CreateDeliveryRecordRequest;
import on.logistics.hubtransitservice.infrastructure.clients.deliveryservice.feign.dtos.CreateDeliveryRecordResponse;
import on.logistics.hubtransitservice.infrastructure.clients.deliveryservice.feign.dtos.UpdateDeliveryStatusRequest;
import on.logistics.hubtransitservice.infrastructure.clients.deliveryservice.feign.dtos.UpdateDeliveryStatusResponse;

public interface DeliveryServiceClient {

    CreateDeliveryRecordResponse createDeliveryRecord(CreateDeliveryRecordRequest request,
        HttpServletRequest httpServletRequest);

    UpdateDeliveryStatusResponse updateDeliveryRecordStatus(UUID id,
        UpdateDeliveryStatusRequest request, HttpServletRequest httpServletRequest);

}
