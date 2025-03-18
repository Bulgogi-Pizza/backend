package on.logistics.orderservice.global.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "on.logistics.orderservice.application.clients")
public class FeignClientConfig {

}
