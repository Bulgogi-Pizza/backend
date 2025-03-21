package on.logistics.companyservice.presentation.dtos.response;

import java.util.UUID;
import on.logistics.companyservice.domain.entity.enums.CompanyStatus;
import on.logistics.companyservice.domain.entity.enums.CompanyType;

public record GetCompanyResponse(UUID companyId, String companyName, CompanyType companyType,
                                 CompanyStatus status,
                                 UUID managedHubId, String companyAddress) {

    public static GetCompanyResponse of(UUID companyId, String companyName, CompanyType companyType,
        CompanyStatus status,
        UUID managedHubId, String companyAddress) {
        return new GetCompanyResponse(companyId, companyName, companyType, status, managedHubId,
            companyAddress);
    }
}