package on.logistics.slackservice.global.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "on.logistics.slackservice.infrastructure.clients")
public class FeignClientConfig {

}
