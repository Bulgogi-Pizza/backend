package on.logistics.deliveryservice.infrastructure.clients.hub;

import feign.Response;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.deliveryservice.global.utils.FeignClientResponseUtils;
import on.logistics.deliveryservice.infrastructure.clients.hub.feign.HubServiceFeignClient;
import on.logistics.deliveryservice.infrastructure.clients.hub.feign.dtos.GetHubInfo;
import on.logistics.deliveryservice.infrastructure.clients.hub.feign.dtos.GetHubManagerBooleanResponse;
import on.logistics.deliveryservice.infrastructure.clients.hub.feign.dtos.GetMiddleHubPageInfo;
import on.logistics.deliveryservice.infrastructure.clients.hub.feign.dtos.GetSpokeHubInfo;
import on.logistics.deliveryservice.infrastructure.clients.hub.feign.dtos.HubInfo;
import on.logistics.deliveryservice.infrastructure.clients.hub.feign.dtos.HubManagerBooleanRequest;
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
        List<HubInfo> hubInfos = (List<HubInfo>) FeignClientResponseUtils.getListBody(response,
            HubInfo.class);
        return GetSpokeHubInfo.from(hubInfos);
    }

    @Override
    public GetHubManagerBooleanResponse getHubManagerBoolean(HubManagerBooleanRequest request) {
        Response response = hubServiceFeignClient.validHubManager(request);
        return FeignClientResponseUtils.getBody(response, GetHubManagerBooleanResponse.class);
    }
}
