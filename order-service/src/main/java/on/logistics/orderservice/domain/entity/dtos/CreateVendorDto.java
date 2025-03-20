package on.logistics.orderservice.domain.entity.dtos;

import java.util.UUID;
import on.logistics.orderservice.application.service.dtos.create.CreateOrderRequestDto.OrdersByVendor;
import on.logistics.orderservice.domain.entity.VendorOrder;

public record CreateVendorDto(
    UUID vendorId,
    String vendorName,
    UUID vendorHubId,
    String vendorHubName,
    VendorOrder vendorOrder
) {

    public static CreateVendorDto of(
        VendorOrder vendorOrder,
        OrdersByVendor ordersByVendor
    ) {
        return new CreateVendorDto(
            ordersByVendor.vendorId(),
            ordersByVendor.vendorName(),
            ordersByVendor.vendorHubId(),
            ordersByVendor.vendorHubName(),
            vendorOrder
        );
    }
}
