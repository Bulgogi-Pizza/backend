package on.logistics.companyservice.presentation.dtos.response;

import java.util.UUID;

public record UpdateCompanyTypeResponse(UUID companyId) {

    public static UpdateCompanyTypeResponse of(UUID companyId) {
        return new UpdateCompanyTypeResponse(companyId);
    }

}