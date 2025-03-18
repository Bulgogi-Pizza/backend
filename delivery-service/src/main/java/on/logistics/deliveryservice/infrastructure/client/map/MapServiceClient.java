package on.logistics.deliveryservice.infrastructure.client.map;

import on.logistics.deliveryservice.infrastructure.client.map.feign.dtos.GetDestinationInfo;
import org.springframework.stereotype.Service;

@Service
public interface MapServiceClient {

    GetDestinationInfo getGeocode(String destination);
}
