package on.logistics.deliveryservice.infrastructure.clients.hubTransit;

import on.logistics.deliveryservice.infrastructure.clients.hubTransit.feign.dtos.CreateHubTransitRouteRequest;
import on.logistics.deliveryservice.infrastructure.clients.hubTransit.feign.dtos.CreateHubTransitRouteResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface HubTransitServiceClient {

    CreateHubTransitRouteResponse createHubTransitRoute(
        @RequestBody CreateHubTransitRouteRequest request);
}
