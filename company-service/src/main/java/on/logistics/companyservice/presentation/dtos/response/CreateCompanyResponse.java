package on.logistics.companyservice.presentation.dtos.response;

import java.util.UUID;
import on.logistics.companyservice.domain.entity.Company;

public record CreateCompanyResponse(UUID companyId) {

    public static CreateCompanyResponse of(Company company) {
        return new CreateCompanyResponse(company.getId());
    }
}
