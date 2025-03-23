package on.logistics.companyservice.application.dtos.request;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import on.logistics.companyservice.presentation.dtos.request.UpdateCompanyRequest;

public record UpdateCompanyRequestDto(UUID companyId, String companyName, String companyAddress,
                                      HttpServletRequest passportRequest) {

    public static UpdateCompanyRequestDto from(UUID companyId, UpdateCompanyRequest dto,
        HttpServletRequest passportRequest) {
        return new UpdateCompanyRequestDto(companyId, dto.companyName(), dto.companyAddress(),
            passportRequest);
    }
}
