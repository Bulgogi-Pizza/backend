package on.logistics.hubservice.application.clients.map;

import on.logistics.hubservice.application.clients.map.feign.dtos.GetGeocodeResponse;
import org.springframework.stereotype.Service;

@Service
public interface MapServiceClient {

    GetGeocodeResponse getGeocode(String query);
}
