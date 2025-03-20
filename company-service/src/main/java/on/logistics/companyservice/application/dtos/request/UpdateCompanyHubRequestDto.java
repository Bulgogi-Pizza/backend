package on.logistics.companyservice.application.dtos.request;

import java.util.UUID;
import on.logistics.companyservice.presentation.dtos.request.UpdateCompanyHubRequest;

public record UpdateCompanyHubRequestDto(UUID managedHubId) {

    public static UpdateCompanyHubRequestDto from(UpdateCompanyHubRequest dto) {
        return new UpdateCompanyHubRequestDto(
            dto.managedHubId());
    }
}
