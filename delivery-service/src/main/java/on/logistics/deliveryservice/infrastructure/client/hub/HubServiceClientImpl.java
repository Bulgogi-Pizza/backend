package on.logistics.deliveryservice.infrastructure.client.hub;

import feign.Response;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.deliveryservice.global.utils.FeignClientResponseUtils;
import on.logistics.deliveryservice.infrastructure.client.hub.feign.HubServiceFeignClient;
import on.logistics.deliveryservice.infrastructure.client.hub.feign.dtos.GetHubInfo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HubServiceClientImpl implements HubServiceClient {

    private HubServiceFeignClient hubServiceFeignClient;
    
    @Override
    public GetHubInfo getHubInfo(UUID hubId) {
        Response response = hubServiceFeignClient.getHubInfo(hubId);
        return FeignClientResponseUtils.getBody(response, GetHubInfo.class);
    }
}
