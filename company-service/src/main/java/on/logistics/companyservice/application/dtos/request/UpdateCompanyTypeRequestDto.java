package on.logistics.companyservice.application.dtos.request;

import java.util.UUID;
import on.logistics.companyservice.domain.entity.enums.CompanyType;

public record UpdateCompanyTypeRequestDto(UUID companyId, CompanyType companyType) {

    public static UpdateCompanyTypeRequestDto from(UUID companyId, CompanyType companyType) {
        return new UpdateCompanyTypeRequestDto(companyId, companyType);
    }
}
