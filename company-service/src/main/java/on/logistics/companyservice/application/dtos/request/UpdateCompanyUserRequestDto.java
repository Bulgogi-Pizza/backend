package on.logistics.companyservice.application.dtos.request;

import java.util.UUID;

public record UpdateCompanyUserRequestDto(UUID companyId, UUID userId) {

    public static UpdateCompanyUserRequestDto of(UUID companyId, UUID userId) {
        return new UpdateCompanyUserRequestDto(companyId, userId);
    }
}
