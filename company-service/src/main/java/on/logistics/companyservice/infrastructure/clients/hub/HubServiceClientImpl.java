package on.logistics.companyservice.infrastructure.clients.hub;

import feign.Response;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.companyservice.global.utils.FeignClientResponseUtils;
import on.logistics.companyservice.infrastructure.clients.hub.feign.HubServiceFeignClient;
import on.logistics.companyservice.infrastructure.clients.hub.feign.dtos.GetHubInfo;
import on.logistics.companyservice.infrastructure.clients.hub.feign.dtos.GetHubManagerBooleanResponse;
import on.logistics.companyservice.infrastructure.clients.hub.feign.dtos.HubManagerBooleanRequest;
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
    public GetHubManagerBooleanResponse getHubManagerBoolean(HubManagerBooleanRequest request) {
        Response response = hubServiceFeignClient.validHubManager(request);
        return FeignClientResponseUtils.getBody(response, GetHubManagerBooleanResponse.class);
    }
}
