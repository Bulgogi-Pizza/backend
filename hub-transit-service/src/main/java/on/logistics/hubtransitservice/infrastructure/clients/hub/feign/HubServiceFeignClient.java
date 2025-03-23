package on.logistics.hubtransitservice.infrastructure.clients.hub.feign;

import feign.Response;
import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "hub-service", url = "hub-service:8080")
public interface HubServiceFeignClient {

    @GetMapping("/api/v1/hubs/{hubId}")
    Response getHubById(@PathVariable("hubId") UUID hubId, HttpServletRequest httpServletRequest);

    @GetMapping("/api/v1/hubs/search")
    Response searchHubs(@RequestParam("keyword") String keyword,
        HttpServletRequest httpServletRequest);

}
