package on.logistics.deliverymanagerservice.infrastructure.clients.user.feign;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserServiceFeignClient {

    @GetMapping("/api/v1/users/{id}")
    Response getUserInfo(@PathVariable String id);
}
