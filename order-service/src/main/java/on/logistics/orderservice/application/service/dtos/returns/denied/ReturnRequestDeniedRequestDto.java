package on.logistics.orderservice.application.service.dtos.returns.denied;

import java.util.UUID;
import on.logistics.orderservice.global.enums.AuthRole;

public record ReturnRequestDeniedRequestDto(
    UUID orderId,
    UUID vendorOrderId,
    UUID userId,
    AuthRole role
) {

    public static ReturnRequestDeniedRequestDto of(
        UUID orderId,
        UUID vendorOrderId,
        UUID userId,
        String role
    ) {
        return new ReturnRequestDeniedRequestDto(
            orderId,
            vendorOrderId,
            userId,
            AuthRole.valueOf(role)
        );
    }
}
