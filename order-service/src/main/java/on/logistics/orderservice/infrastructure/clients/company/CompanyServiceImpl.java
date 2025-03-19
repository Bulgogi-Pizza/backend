package on.logistics.orderservice.infrastructure.clients.company;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.orderservice.application.service.CompanyService;
import on.logistics.orderservice.infrastructure.clients.company.feign.CompanyServiceFeignClient;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

  private final CompanyServiceFeignClient companyServiceFeignClient;

}
