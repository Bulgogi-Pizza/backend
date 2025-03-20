package on.logistics.deliveryservice.infrastructure.clients.map;

import on.logistics.deliveryservice.infrastructure.clients.map.feign.dtos.GetDestinationInfo;
import on.logistics.deliveryservice.infrastructure.clients.map.feign.dtos.GetEstimateInfo;
import on.logistics.deliveryservice.infrastructure.clients.map.feign.dtos.GetHubRouteInfo;
import org.springframework.stereotype.Service;

@Service
public interface MapServiceClient {

    GetDestinationInfo getGeocode(String destination);

    GetEstimateInfo getEstimate(String start, String end);

    GetHubRouteInfo getRoute(String start, String end);
}
