package on.logistics.orderservice.infrastructure.clients.company.feign.dtos;

import java.util.UUID;

public record GetCompanyResponse(
    UUID userId,
    String companyName,
    String companyType,
    UUID manageHubId,
    String companyAddress
) {

}
