package on.logistics.deliveryservice.infrastructure.client.hub;

import java.util.UUID;
import on.logistics.deliveryservice.infrastructure.client.hub.feign.dtos.GetHubInfo;

public interface HubServiceClient {

    GetHubInfo getHubInfo(UUID hubId);
}
