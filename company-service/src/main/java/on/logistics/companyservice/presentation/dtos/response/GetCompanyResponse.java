package on.logistics.companyservice.presentation.dtos.response;

import java.util.UUID;
import on.logistics.companyservice.domain.entity.enums.CompanyStatus;
import on.logistics.companyservice.domain.entity.enums.CompanyType;

public record GetCompanyResponse(UUID companyId, UUID userId, String companyName,
                                 CompanyType companyType,
                                 CompanyStatus companyStatus,
                                 UUID managedHubId, String companyAddress) {

    public static GetCompanyResponse of(UUID companyId, UUID userId, String companyName,
        CompanyType companyType,
        CompanyStatus companyStatus,
        UUID managedHubId, String companyAddress) {
        return new GetCompanyResponse(companyId, userId, companyName, companyType, companyStatus,
            managedHubId,
            companyAddress);
    }
}