package on.logistics.orderservice.infrastructure.clients.slack.feign;

import feign.Response;
import on.logistics.orderservice.infrastructure.clients.slack.feign.dtos.SendMessageRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "slack-service")
public interface SlackServiceFeignClient {

    @PostMapping("/api/v1/slack")
    Response sendMessage(@RequestBody SendMessageRequest request);
}
