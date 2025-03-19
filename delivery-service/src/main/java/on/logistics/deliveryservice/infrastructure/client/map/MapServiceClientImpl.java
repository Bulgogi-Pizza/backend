package on.logistics.deliveryservice.infrastructure.client.map;

import feign.Response;
import lombok.RequiredArgsConstructor;
import on.logistics.deliveryservice.global.utils.FeignClientResponseUtils;
import on.logistics.deliveryservice.infrastructure.client.map.feign.MapServiceFeignClient;
import on.logistics.deliveryservice.infrastructure.client.map.feign.dtos.GetDestinationInfo;
import on.logistics.deliveryservice.infrastructure.client.map.feign.dtos.GetEstimateInfo;
import on.logistics.deliveryservice.infrastructure.client.map.feign.dtos.GetHubRouteInfo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapServiceClientImpl implements MapServiceClient {

    private final MapServiceFeignClient mapServiceFeignClient;

    @Override
    public GetDestinationInfo getGeocode(String destination) {
        Response response = mapServiceFeignClient.getGeocode(destination);
        return FeignClientResponseUtils.getBody(response, GetDestinationInfo.class);
    }

    @Override
    public GetEstimateInfo getEstimate(String start, String end) {
        Response response = mapServiceFeignClient.getRoute(start, end);
        return FeignClientResponseUtils.getBody(response, GetEstimateInfo.class);
    }

    @Override
    public GetHubRouteInfo getRoute(String start, String end) {
        Response response = mapServiceFeignClient.getRoute(start, end);
        return FeignClientResponseUtils.getBody(response, GetHubRouteInfo.class);
    }
}
