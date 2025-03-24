package on.logistics.orderservice.infrastructure.clients.company.dtos;

import java.util.UUID;

public record GetCompanyResponseDto(
    UUID companyId,
    UUID userId,
    String companyName,
    CompanyType companyType,
    CompanyStatus companyStatus,
    UUID managedHubId,
    String companyAddress
) {

    private enum CompanyType {
        PRODUCTION, RECEIVING
    }

    private enum CompanyStatus {
        PENDING, APPROVED, REJECTED
    }
}
