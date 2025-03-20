package on.logistics.deliveryservice.infrastructure.clients.hub.feign;

import feign.Response;
import java.util.UUID;
import on.logistics.deliveryservice.infrastructure.clients.hub.feign.dtos.HubType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "hub-service")
public interface HubServiceFeignClient {

    @GetMapping("/api/v1/hubs/{hubId}")
    Response getHubInfo(@PathVariable UUID hubId);

    @GetMapping("/api/v1/hubs/search")
    Response searchHubs(@RequestParam("type") HubType type);

    @GetMapping("/api/v1/hubs/link")
    Response getSpokeHubInfo(@RequestParam("centerId") UUID centerId);
}
