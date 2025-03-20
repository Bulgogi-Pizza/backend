package on.logistics.companyservice.presentation.dtos.request;

import jakarta.validation.constraints.NotNull;
import on.logistics.companyservice.domain.entity.enums.CompanyType;

public record UpdateCompanyTypeRequest(
    @NotNull(message = "업체 타입은 반드시 입력되어야 합니다.") CompanyType companyType) {

}
