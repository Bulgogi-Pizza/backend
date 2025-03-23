package on.logistics.deliveryservice.infrastructure.clients.map.feign;

import feign.Response;
import on.logistics.deliveryservice.global.configuration.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "map-service", configuration = FeignClientConfig.class)
public interface MapServiceFeignClient {

    @GetMapping("/api/v1/maps/geocode")
    Response getGeocode(@RequestParam("query") String query);

    @GetMapping("/api/v1/maps/route")
    Response getRoute(@RequestParam("start") String start, @RequestParam("end") String end);
}
