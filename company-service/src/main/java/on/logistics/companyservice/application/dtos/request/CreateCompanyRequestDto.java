package on.logistics.companyservice.application.dtos.request;

import on.logistics.companyservice.domain.entity.enums.CompanyType;
import on.logistics.companyservice.presentation.dtos.request.CreateCompanyRequest;

public record CreateCompanyRequestDto(String companyName, CompanyType companyType,
                                      String companyAddress) {

    public static CreateCompanyRequestDto from(CreateCompanyRequest dto) {
        return new CreateCompanyRequestDto(dto.companyName(), dto.companyType(),
            dto.companyAddress());
    }

}
