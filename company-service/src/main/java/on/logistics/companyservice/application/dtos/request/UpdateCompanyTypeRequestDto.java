package on.logistics.companyservice.application.dtos.request;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import on.logistics.companyservice.domain.entity.enums.CompanyType;

public record UpdateCompanyTypeRequestDto(UUID companyId, CompanyType companyType,
                                          HttpServletRequest passportRequest) {

    public static UpdateCompanyTypeRequestDto from(UUID companyId, CompanyType companyType,
        HttpServletRequest passportRequest) {
        return new UpdateCompanyTypeRequestDto(companyId, companyType, passportRequest);
    }
}
