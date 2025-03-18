package on.logistics.orderservice.application.service;

import java.util.UUID;
import on.logistics.orderservice.infrastructure.clients.company.feign.dtos.GetCompanyResponse;

public interface CompanyService {

  GetCompanyResponse getCompanyById(UUID companyId);
}
