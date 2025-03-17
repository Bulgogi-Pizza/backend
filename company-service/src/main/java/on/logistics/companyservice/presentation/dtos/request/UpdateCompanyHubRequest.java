package on.logistics.companyservice.presentation.dtos.request;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
import on.logistics.companyservice.application.dtos.request.UpdateCompanyHubRequestDto;

public record UpdateCompanyHubRequest(@NotBlank UUID managedHubId) {

    public static UpdateCompanyHubRequestDto from(UpdateCompanyHubRequest dto) {
        return new UpdateCompanyHubRequestDto(
            dto.managedHubId());
    }

}
