package on.logistics.orderservice.infrastructure.clients.company.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "company-service")
public interface CompanyServiceFeignClient {

}
