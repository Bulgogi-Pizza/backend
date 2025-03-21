package on.logistics.productservice.infrastructure.clients.hub;

import feign.Response;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.productservice.global.utils.FeignClientResponseUtils;
import on.logistics.productservice.infrastructure.clients.hub.feign.HubServiceFeignClient;
import on.logistics.productservice.infrastructure.clients.hub.feign.dtos.GetHubInfo;
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
}
