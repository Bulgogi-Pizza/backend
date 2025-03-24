package on.logistics.orderservice.infrastructure.clients.company.dtos;

import java.util.UUID;

public record GetCompanyResponseDto(
    UUID companyId,
    String companyName,
    CompanyType companyType,
    UUID managedHubId,
    String companyAddress
) {

    private enum CompanyType {
        PRODUCTION, RECEIVING
    }
}
