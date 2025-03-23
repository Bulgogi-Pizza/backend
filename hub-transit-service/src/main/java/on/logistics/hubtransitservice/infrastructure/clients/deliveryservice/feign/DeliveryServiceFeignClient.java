package on.logistics.hubtransitservice.infrastructure.clients.deliveryservice.feign;

import feign.Response;
import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import on.logistics.hubtransitservice.infrastructure.clients.deliveryservice.feign.dtos.CreateDeliveryRecordRequest;
import on.logistics.hubtransitservice.infrastructure.clients.deliveryservice.feign.dtos.UpdateDeliveryStatusRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "delivery-service", url = "delivery-service:8080")
public interface DeliveryServiceFeignClient {

    @PostMapping("/api/v1/delivery/record")
    Response createDeliveryRecord(@RequestBody CreateDeliveryRecordRequest request,
        HttpServletRequest httpServletRequest);

    @PutMapping("/api/v1/delivery/record/status/{id}")
    Response updateDeliveryRecordStatus(
        @PathVariable("id") UUID id,
        @RequestBody UpdateDeliveryStatusRequest request,
        HttpServletRequest httpServletRequest
    );

}
