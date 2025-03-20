package on.logistics.companyservice.presentation.dtos.response;

import java.util.UUID;

public record UpdateCompanyUserResponse(UUID companyId) {

    public static UpdateCompanyUserResponse of(UUID companyId) {
        return new UpdateCompanyUserResponse(companyId);
    }
}
