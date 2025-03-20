package on.logistics.productservice.infrastructure.clients.company;

import java.util.UUID;
import on.logistics.productservice.infrastructure.clients.company.feign.dtos.GetCompanyInfo;

public interface CompanyServiceClient {

    GetCompanyInfo getCompanyInfo(UUID companyId);
}
