package on.logistics.hubservice.infrastructure.clients.hubtransit;

import on.logistics.hubservice.infrastructure.clients.hubtransit.feign.dtos.request.NextHubTransitRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface HubTransitServiceClient {

    void requestNextHubTransit(@RequestBody NextHubTransitRequest request);
}
