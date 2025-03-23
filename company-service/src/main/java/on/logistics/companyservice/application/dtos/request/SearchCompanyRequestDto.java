package on.logistics.companyservice.application.dtos.request;

import on.logistics.companyservice.domain.entity.enums.CompanyStatus;
import on.logistics.companyservice.domain.entity.enums.CompanyType;
import org.springframework.data.domain.Pageable;

public record SearchCompanyRequestDto(String name, CompanyType type, CompanyStatus status,
                                      Pageable pageable) {

    public static SearchCompanyRequestDto from(String name, CompanyType type, CompanyStatus status,
        Pageable pageable) {
        return new SearchCompanyRequestDto(name, type, status, pageable);
    }

}
