package on.logistics.companyservice.presentation.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import on.logistics.companyservice.application.dtos.request.CreateCompanyRequestDto;
import on.logistics.companyservice.domain.entity.enums.CompanyType;


public record CreateCompanyRequest(@NotBlank @Size(max = 100) String companyName,
                                   @NotBlank CompanyType companyType,
                                   @NotBlank String companyAddress) {

    public static CreateCompanyRequestDto from(CreateCompanyRequest dto) {
        return new CreateCompanyRequestDto(
            dto.companyName(),
            dto.companyType(),
            dto.companyAddress()
        );
    }
}
