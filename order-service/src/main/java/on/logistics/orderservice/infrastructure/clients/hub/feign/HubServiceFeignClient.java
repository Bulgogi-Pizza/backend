package on.logistics.orderservice.infrastructure.clients.hub.feign;

import feign.Response;
import java.util.UUID;
import on.logistics.orderservice.infrastructure.clients.hub.feign.dtos.ValidateHubManagerRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "hub-service")
public interface HubServiceFeignClient {

    @GetMapping("/api/v1/hubs/{hubId}")
    Response getHubById(@PathVariable UUID hubId);

    @GetMapping("/api/v1/hubs/manager/valid")
    Response validateHubManager(@RequestBody ValidateHubManagerRequest request);

    @GetMapping("/api/v1/hubs/manager/{hubId}/manager")
    Response getHubManagerId(@PathVariable UUID hubId);
}
