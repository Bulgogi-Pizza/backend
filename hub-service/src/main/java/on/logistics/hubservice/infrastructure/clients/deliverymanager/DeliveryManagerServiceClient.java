package on.logistics.hubservice.infrastructure.clients.deliverymanager;

import on.logistics.hubservice.application.dtos.request.AssignDeliveryManagerRequestDto;
import on.logistics.hubservice.infrastructure.clients.deliverymanager.feign.dtos.response.AssignDeliveryManagerResponse;
import org.springframework.stereotype.Service;

@Service
public interface DeliveryManagerServiceClient {

    AssignDeliveryManagerResponse assignDeliveryManager(AssignDeliveryManagerRequestDto request);
}
