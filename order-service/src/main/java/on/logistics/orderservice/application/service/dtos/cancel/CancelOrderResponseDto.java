package on.logistics.orderservice.application.service.dtos.cancel;

import java.util.UUID;
import on.logistics.orderservice.domain.entity.VendorOrder;

public record CancelOrderResponseDto(
    UUID vendorOrderId
) {

    public static CancelOrderResponseDto from(VendorOrder vendorOrder) {
        return new CancelOrderResponseDto(vendorOrder.getId());
    }
}
