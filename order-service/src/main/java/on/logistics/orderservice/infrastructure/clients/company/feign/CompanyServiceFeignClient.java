package on.logistics.orderservice.infrastructure.clients.company.feign;

import feign.Response;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "company-service")
public interface CompanyServiceFeignClient {

  @GetMapping("/api/v1/companies/{ordererId}")
  Response getCompanyById(@PathVariable UUID ordererId);
}
