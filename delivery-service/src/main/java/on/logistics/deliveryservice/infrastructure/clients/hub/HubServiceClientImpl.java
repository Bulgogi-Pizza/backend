package on.logistics.deliveryservice.infrastructure.clients.hub;

import feign.Response;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.deliveryservice.global.utils.FeignClientResponseUtils;
import on.logistics.deliveryservice.infrastructure.clients.hub.feign.HubServiceFeignClient;
import on.logistics.deliveryservice.infrastructure.clients.hub.feign.dtos.GetHubInfo;
import on.logistics.deliveryservice.infrastructure.clients.hub.feign.dtos.GetMiddleHubPageInfo;
import on.logistics.deliveryservice.infrastructure.clients.hub.feign.dtos.GetSpokeHubInfo;
import on.logistics.deliveryservice.infrastructure.clients.hub.feign.dtos.HubType;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HubServiceClientImpl implements HubServiceClient {

    private final HubServiceFeignClient hubServiceFeignClient;

    @Override
    public GetHubInfo getHubInfo(UUID hubId) {
        Response response = hubServiceFeignClient.getHubInfo(hubId);
        return FeignClientResponseUtils.getBody(response, GetHubInfo.class);
    }

    @Override
    public GetMiddleHubPageInfo searchHubs(HubType type) {
        Response response = hubServiceFeignClient.searchHubs(type);
        return FeignClientResponseUtils.getBody(response, GetMiddleHubPageInfo.class);
    }

    @Override
    public GetSpokeHubInfo getSpokeHubInfo(UUID hubId) {
        Response response = hubServiceFeignClient.getSpokeHubInfo(hubId);
        return FeignClientResponseUtils.getBody(response, GetSpokeHubInfo.class);
    }
}
