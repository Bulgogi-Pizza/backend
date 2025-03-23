package on.logistics.hubservice.infrastructure.clients.map;

import on.logistics.hubservice.infrastructure.clients.map.feign.dtos.GetGeocodeResponse;
import org.springframework.stereotype.Service;

@Service
public interface MapServiceClient {

    GetGeocodeResponse getGeocode(String query);
}
