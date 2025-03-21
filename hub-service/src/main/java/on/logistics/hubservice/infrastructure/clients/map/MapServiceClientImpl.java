package on.logistics.hubservice.infrastructure.clients.map;

import feign.Response;
import lombok.RequiredArgsConstructor;
import on.logistics.hubservice.global.util.FeignClientResponseUtils;
import on.logistics.hubservice.infrastructure.clients.map.feign.MapServiceFeignClient;
import on.logistics.hubservice.infrastructure.clients.map.feign.dtos.GetGeocodeResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapServiceClientImpl implements MapServiceClient {

    private final MapServiceFeignClient mapServiceFeignClient;

    @Override
    public GetGeocodeResponse getGeocode(String query) {
        Response response = mapServiceFeignClient.getGeocode(query);
        return FeignClientResponseUtils.getBody(response, GetGeocodeResponse.class);
    }
}
