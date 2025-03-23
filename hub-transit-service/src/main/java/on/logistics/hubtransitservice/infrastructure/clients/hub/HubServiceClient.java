package on.logistics.hubtransitservice.infrastructure.clients.hub;

import java.util.UUID;
import on.logistics.hubtransitservice.infrastructure.clients.hub.feign.dtos.GetHubResponse;

public interface HubServiceClient {

    GetHubResponse getHubById(UUID hubId);

    GetHubResponse getHubByName(String hubName);
}
