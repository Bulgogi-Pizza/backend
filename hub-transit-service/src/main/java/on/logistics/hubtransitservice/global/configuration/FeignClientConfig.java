package on.logistics.hubtransitservice.global.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "on.logistics.hubtransitservice.infrastructure.clients")
public class FeignClientConfig {

}
