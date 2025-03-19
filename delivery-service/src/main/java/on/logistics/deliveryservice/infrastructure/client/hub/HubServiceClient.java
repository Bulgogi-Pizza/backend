package on.logistics.deliveryservice.infrastructure.client.hub;

import java.util.UUID;
import on.logistics.deliveryservice.infrastructure.client.hub.feign.dtos.GetHubInfo;
import on.logistics.deliveryservice.infrastructure.client.hub.feign.dtos.GetMiddleHubPageInfo;
import on.logistics.deliveryservice.infrastructure.client.hub.feign.dtos.GetSpokeHubInfo;
import on.logistics.deliveryservice.infrastructure.client.hub.feign.dtos.HubType;
import org.springframework.web.bind.annotation.RequestParam;

public interface HubServiceClient {

    GetHubInfo getHubInfo(UUID hubId);

    GetMiddleHubPageInfo searchHubs(@RequestParam("type") HubType type);

    GetSpokeHubInfo getSpokeHubInfo(UUID hubId);

}
