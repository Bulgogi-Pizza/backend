package on.logistics.deliveryservice.infrastructure.clients.hubTransit.feign;

import feign.Response;
import on.logistics.deliveryservice.global.configuration.FeignClientConfig;
import on.logistics.deliveryservice.infrastructure.clients.hubTransit.feign.dtos.CreateHubTransitRouteRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "hub-transit-service", configuration = FeignClientConfig.class)
public interface HubTransitServiceFeignClient {

    @PostMapping("/api/v1/hub-transit/route")
    Response createHubTransitRoute(@RequestBody CreateHubTransitRouteRequest request);

}
