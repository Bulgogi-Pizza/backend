package on.logistics.companyservice.application.dtos.request;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;

public record UpdateCompanyUserRequestDto(UUID companyId, UUID userId,
                                          HttpServletRequest passportRequest) {

    public static UpdateCompanyUserRequestDto of(UUID companyId, UUID userId,
        HttpServletRequest passportRequest) {
        return new UpdateCompanyUserRequestDto(companyId, userId, passportRequest);
    }
}
