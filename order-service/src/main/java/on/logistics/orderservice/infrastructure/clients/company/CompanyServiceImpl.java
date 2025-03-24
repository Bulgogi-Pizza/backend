package on.logistics.orderservice.infrastructure.clients.company;

import feign.Response;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.orderservice.application.service.CompanyService;
import on.logistics.orderservice.global.utils.FeignClientResponseUtils;
import on.logistics.orderservice.infrastructure.clients.company.dtos.GetCompanyResponseDto;
import on.logistics.orderservice.infrastructure.clients.company.feign.CompanyServiceFeignClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    private final CompanyServiceFeignClient companyServiceFeignClient;

    @Override
    public GetCompanyResponseDto getCompanyById(UUID companyId) {
        log.info("Getting company by id: {}", companyId);
        Response response = companyServiceFeignClient.getCompanyById(companyId);
        return FeignClientResponseUtils.getBody(response, GetCompanyResponseDto.class);
    }
}
