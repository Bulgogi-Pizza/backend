package on.logistics.productservice.infrastructure.clients.hub;

import java.util.UUID;
import on.logistics.productservice.infrastructure.clients.hub.feign.dtos.GetHubInfo;

public interface HubServiceClient {

    GetHubInfo getHubInfo(UUID hubId);

}
