package on.logistics.companyservice.presentation.dtos.response;

import java.util.UUID;
import on.logistics.companyservice.domain.entity.Company;
import on.logistics.companyservice.domain.entity.enums.CompanyStatus;

public record SearchCompanyResponse(UUID companyId, UUID userId, String companyName,
                                    CompanyStatus status,
                                    UUID managedHubId) {

    public static SearchCompanyResponse from(Company company) {
        return new SearchCompanyResponse(company.getId(), company.getUserId(),
            company.getName().getValue(), company.getStatus(), company.getManagedHubId());
    }

}