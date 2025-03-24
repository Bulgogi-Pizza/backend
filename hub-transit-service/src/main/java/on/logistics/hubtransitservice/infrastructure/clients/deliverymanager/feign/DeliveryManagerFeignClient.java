package on.logistics.hubtransitservice.infrastructure.clients.deliverymanager.feign;

import feign.Response;
import on.logistics.hubtransitservice.infrastructure.clients.deliverymanager.feign.dtos.AssignDeliveryManagerRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "delivery-manager-service")
public interface DeliveryManagerFeignClient {

    @PostMapping("/api/v1/delivery-managers/assign")
    Response assignDeliveryManager(@RequestBody AssignDeliveryManagerRequest request);

}
