package on.logistics.hubservice.application.clients.map;

import feign.Response;
import lombok.RequiredArgsConstructor;
import on.logistics.hubservice.application.clients.map.feign.MapServiceFeignClient;
import on.logistics.hubservice.application.clients.map.feign.dtos.GetGeocodeResponse;
import on.logistics.hubservice.global.utills.FeignClientResponseUtils;
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
