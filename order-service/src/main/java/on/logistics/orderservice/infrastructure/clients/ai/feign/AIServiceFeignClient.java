package on.logistics.orderservice.infrastructure.clients.ai.feign;

import feign.Response;
import on.logistics.orderservice.infrastructure.clients.ai.feign.dtos.GenerateShippingDeadlineRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ai-service")
public interface AIServiceFeignClient {

  @GetMapping("/api/v1/ai/shipping-deadline")
  Response generateShippingDeadline(@RequestBody GenerateShippingDeadlineRequest request);
}
