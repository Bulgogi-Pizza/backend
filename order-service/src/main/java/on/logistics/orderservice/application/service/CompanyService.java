package on.logistics.orderservice.application.service;

import java.util.UUID;
import on.logistics.orderservice.infrastructure.clients.company.dtos.GetCompanyResponseDto;

public interface CompanyService {

    GetCompanyResponseDto getCompanyById(UUID companyId);
}
