package on.logistics.deliveryservice.infrastructure.client.map.feign;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "map-service")
public interface MapServiceFeignClient {

    @GetMapping("/api/v1/maps/geocode")
    Response getGeocode(@RequestParam("query") String query);
}
