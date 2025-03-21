package on.logistics.companyservice.application.dtos.request;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import on.logistics.companyservice.domain.entity.enums.CompanyType;
import on.logistics.companyservice.presentation.dtos.request.CreateCompanyRequest;

public record CreateCompanyRequestDto(String companyName, CompanyType companyType,
                                      String companyAddress, UUID managedHubId,
                                      HttpServletRequest passportRequest) {

    public static CreateCompanyRequestDto from(CreateCompanyRequest dto,
        HttpServletRequest passportRequest) {
        return new CreateCompanyRequestDto(dto.companyName(), dto.companyType(),
            dto.companyAddress(), dto.managedHubId(), passportRequest);
    }

}
