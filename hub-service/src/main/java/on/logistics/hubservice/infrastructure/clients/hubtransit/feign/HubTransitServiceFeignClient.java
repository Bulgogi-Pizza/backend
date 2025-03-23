package on.logistics.hubservice.infrastructure.clients.hubtransit.feign;

import feign.Response;
import on.logistics.hubservice.infrastructure.clients.hubtransit.feign.dtos.request.NextHubTransitRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "hub-transit-service")
public interface HubTransitServiceFeignClient {

    @PostMapping("/api/v1/hub-transit/route/next")
    Response requestNextHubTransit(@RequestBody NextHubTransitRequest request);
}
