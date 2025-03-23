package on.logistics.productservice.infrastructure.clients.hub.feign;

import feign.Response;
import java.util.UUID;
import on.logistics.productservice.global.configuration.FeignClientConfig;
import on.logistics.productservice.infrastructure.clients.hub.feign.dtos.HubManagerBooleanRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "hub-service", configuration = FeignClientConfig.class)
public interface HubServiceFeignClient {

    @GetMapping("/api/v1/hubs/{hubId}")
    Response getHubInfo(@PathVariable UUID hubId);

    @PostMapping("/api/v1/hubs/manager/valid")
    Response validHubManager(@RequestBody HubManagerBooleanRequest request);

}
