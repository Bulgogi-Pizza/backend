package on.logistics.orderservice.infrastructure.clients.company;

import feign.Response;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.orderservice.application.service.CompanyService;
import on.logistics.orderservice.infrastructure.clients.company.feign.CompanyServiceFeignClient;
import on.logistics.orderservice.infrastructure.clients.company.feign.dtos.GetCompanyResponse;
import on.logistics.orderservice.global.utils.FeignClientResponseUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

  private final CompanyServiceFeignClient companyServiceFeignClient;

  @Override
  public GetCompanyResponse getCompanyById(UUID companyId) {
    log.info("회사 조회 요청");
    Response response = companyServiceFeignClient.getCompanyById(companyId);
    return FeignClientResponseUtils.getBody(response, GetCompanyResponse.class);
  }
}
