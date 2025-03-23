package on.logistics.companyservice.application.dtos.request;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import on.logistics.companyservice.presentation.dtos.request.UpdateCompanyHubRequest;

public record UpdateCompanyHubRequestDto(UUID managedHubId, HttpServletRequest passportRequest) {

    public static UpdateCompanyHubRequestDto from(UpdateCompanyHubRequest dto,
        HttpServletRequest passportRequest) {
        return new UpdateCompanyHubRequestDto(dto.managedHubId(), passportRequest);
    }
}
