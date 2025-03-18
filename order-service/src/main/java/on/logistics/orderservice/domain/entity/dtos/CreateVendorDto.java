package on.logistics.orderservice.domain.entity.dtos;

import java.util.UUID;
import on.logistics.orderservice.application.clients.company.feign.dtos.GetCompanyResponse;
import on.logistics.orderservice.application.dtos.create.CreateOrderRequestDto.OrdersByVendor;
import on.logistics.orderservice.domain.entity.VendorOrder;

public record CreateVendorDto(
    UUID vendorId,
    String vendorName,
    UUID vendorHubId,
    VendorOrder vendorOrder
) {

  public static CreateVendorDto of(
      OrdersByVendor ordersByVendor,
      GetCompanyResponse companyResponseDto,
      VendorOrder vendorOrder
  ) {
    return new CreateVendorDto(
        ordersByVendor.vendorId(),
        companyResponseDto.companyName(),
        companyResponseDto.manageHubId(),
        vendorOrder
    );
  }
}
