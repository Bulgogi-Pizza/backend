package on.logistics.productservice.infrastructure.clients.company.feign;

import feign.Response;
import java.util.UUID;
import on.logistics.productservice.global.configuration.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "company-service", configuration = FeignClientConfig.class)
public interface CompanyServiceFeignClient {

    @GetMapping("/api/v1/company/{id}")
    Response getCompany(@PathVariable UUID id);
}
