package on.logistics.orderservice.application.clients.company.feign.dtos;

import java.util.UUID;

public record GetCompanyResponse(
    UUID userId,
    String companyName,
    String companyType,
    UUID manageHubId,
    String companyAddress
) {

}
