package on.logistics.deliveryservice.infrastructure.clients.hubTransit;

import feign.Response;
import lombok.RequiredArgsConstructor;
import on.logistics.deliveryservice.global.utils.FeignClientResponseUtils;
import on.logistics.deliveryservice.infrastructure.clients.hubTransit.feign.HubTransitServiceFeignClient;
import on.logistics.deliveryservice.infrastructure.clients.hubTransit.feign.dtos.CreateHubTransitRouteRequest;
import on.logistics.deliveryservice.infrastructure.clients.hubTransit.feign.dtos.CreateHubTransitRouteResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HubTransitServiceClientImpl implements HubTransitServiceClient {

    private final HubTransitServiceFeignClient hubTransitServiceFeignClient;

    @Override
    public CreateHubTransitRouteResponse createHubTransitRoute(
        CreateHubTransitRouteRequest request) {
        Response response = hubTransitServiceFeignClient.createHubTransitRoute(request);
        return FeignClientResponseUtils.getBody(response, CreateHubTransitRouteResponse.class);
    }
}
