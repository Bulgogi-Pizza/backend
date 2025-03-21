package on.logistics.orderservice.application.service.dtos.returns.request;

import java.util.UUID;
import on.logistics.orderservice.domain.entity.VendorOrder;

public record ReturnRequestResponseDto(
    UUID vendorOrderId
) {

    public static ReturnRequestResponseDto from(VendorOrder vendorOrder) {
        return new ReturnRequestResponseDto(vendorOrder.getId());
    }
}
