package on.logistics.orderservice.application.service.dtos.cancel;

import java.util.UUID;
import on.logistics.orderservice.global.enums.AuthRole;

public record CancelOrderRequestDto(
    UUID orderId,
    UUID vendorOrderId,
    UUID userId,
    AuthRole role
) {

    public static CancelOrderRequestDto of(
        UUID orderId,
        UUID vendorOrderId,
        UUID userId,
        String role
    ) {
        return new CancelOrderRequestDto(
            orderId,
            vendorOrderId,
            userId,
            AuthRole.valueOf(role)
        );
    }
}
