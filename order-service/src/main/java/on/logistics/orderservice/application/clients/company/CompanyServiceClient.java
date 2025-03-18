package on.logistics.orderservice.application.clients.company;

import java.util.UUID;
import on.logistics.orderservice.application.clients.company.feign.dtos.GetCompanyResponse;

public interface CompanyServiceClient {

  GetCompanyResponse getCompanyById(UUID companyId);
}
