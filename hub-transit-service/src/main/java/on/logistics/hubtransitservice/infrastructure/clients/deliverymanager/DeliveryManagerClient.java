package on.logistics.hubtransitservice.infrastructure.clients.deliverymanager;

import on.logistics.hubtransitservice.infrastructure.clients.deliverymanager.feign.dtos.AssignDeliveryManagerRequest;
import on.logistics.hubtransitservice.infrastructure.clients.deliverymanager.feign.dtos.AssignDeliveryManagerResponse;

public interface DeliveryManagerClient {

    AssignDeliveryManagerResponse assignDeliveryManager(AssignDeliveryManagerRequest request);

}
