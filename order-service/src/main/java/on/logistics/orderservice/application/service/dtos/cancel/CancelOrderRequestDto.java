package on.logistics.orderservice.application.service.dtos.cancel;

import java.util.UUID;

public record CancelOrderRequestDto(
    UUID orderId,
    UUID vendorOrderId
) {

    public static CancelOrderRequestDto of(UUID orderId, UUID vendorOrderId) {
        return new CancelOrderRequestDto(
            orderId,
            vendorOrderId
        );
    }
}
