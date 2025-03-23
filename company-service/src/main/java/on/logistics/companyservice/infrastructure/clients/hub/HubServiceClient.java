package on.logistics.companyservice.infrastructure.clients.hub;

import java.util.UUID;
import on.logistics.companyservice.infrastructure.clients.hub.feign.dtos.GetHubInfo;
import on.logistics.companyservice.infrastructure.clients.hub.feign.dtos.GetHubManagerBooleanResponse;
import on.logistics.companyservice.infrastructure.clients.hub.feign.dtos.HubManagerBooleanRequest;

public interface HubServiceClient {

    GetHubInfo getHubInfo(UUID hubId);

    GetHubManagerBooleanResponse getHubManagerBoolean(HubManagerBooleanRequest request);

}
