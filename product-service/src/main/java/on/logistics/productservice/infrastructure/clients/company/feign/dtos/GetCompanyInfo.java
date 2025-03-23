package on.logistics.productservice.infrastructure.clients.company.feign.dtos;

import java.util.UUID;

public record GetCompanyInfo(UUID companyId, UUID userId, String companyName,
                             CompanyType companyType,
                             CompanyStatus companyStatus,
                             UUID managedHubId, String companyAddress) {

}
