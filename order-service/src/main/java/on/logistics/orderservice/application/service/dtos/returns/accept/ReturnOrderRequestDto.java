package on.logistics.orderservice.application.service.dtos.returns.accept;

import java.util.UUID;
import on.logistics.orderservice.global.enums.AuthRole;

public record ReturnOrderRequestDto(
    UUID orderId,
    UUID vendorOrderId,
    UUID userId,
    AuthRole role
) {

    public static ReturnOrderRequestDto of(
        UUID orderId,
        UUID vendorOrderId,
        UUID userId,
        String role
    ) {
        return new ReturnOrderRequestDto(
            orderId,
            vendorOrderId,
            userId,
            AuthRole.valueOf(role)
        );
    }
}
