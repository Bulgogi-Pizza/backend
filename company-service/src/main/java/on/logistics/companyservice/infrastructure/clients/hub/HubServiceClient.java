package on.logistics.companyservice.infrastructure.clients.hub;

import java.util.UUID;
import on.logistics.companyservice.infrastructure.clients.hub.feign.dtos.GetHubInfo;

public interface HubServiceClient {

    GetHubInfo getHubInfo(UUID hubId);

}
