package on.logistics.hubservice.infrastructure.clients.hubtransit;

import lombok.RequiredArgsConstructor;
import on.logistics.hubservice.infrastructure.clients.hubtransit.feign.HubTransitServiceFeignClient;
import on.logistics.hubservice.infrastructure.clients.hubtransit.feign.dtos.request.NextHubTransitRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HubTransitServiceClientImpl implements HubTransitServiceClient {

    private final HubTransitServiceFeignClient hubTransitServiceFeignClient;

    @Override
    public void requestNextHubTransit(NextHubTransitRequest request) {
        hubTransitServiceFeignClient.requestNextHubTransit(request);
    }
}
