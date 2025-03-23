package on.logistics.hubtransitservice.infrastructure.clients.deliverymanager;

import jakarta.servlet.http.HttpServletRequest;
import on.logistics.hubtransitservice.infrastructure.clients.deliverymanager.feign.dtos.AssignDeliveryManagerRequest;
import on.logistics.hubtransitservice.infrastructure.clients.deliverymanager.feign.dtos.AssignDeliveryManagerResponse;

public interface DeliveryManagerClient {

    AssignDeliveryManagerResponse assignDeliveryManager(AssignDeliveryManagerRequest request,
        HttpServletRequest httpServletRequest);

}
