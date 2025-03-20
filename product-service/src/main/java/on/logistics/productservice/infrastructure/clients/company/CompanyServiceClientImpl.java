package on.logistics.productservice.infrastructure.clients.company;

import feign.Response;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.productservice.global.utils.FeignClientResponseUtils;
import on.logistics.productservice.infrastructure.clients.company.feign.CompanyServiceFeignClient;
import on.logistics.productservice.infrastructure.clients.company.feign.dtos.GetCompanyInfo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceClientImpl implements CompanyServiceClient {

    private final CompanyServiceFeignClient companyServiceFeignClient;

    @Override
    public GetCompanyInfo getCompanyInfo(UUID companyId) {
        Response response = companyServiceFeignClient.getCompany(companyId);
        return FeignClientResponseUtils.getBody(response, GetCompanyInfo.class);
    }
}
