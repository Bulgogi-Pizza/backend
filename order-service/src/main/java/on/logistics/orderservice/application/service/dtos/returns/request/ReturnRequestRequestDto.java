package on.logistics.orderservice.application.service.dtos.returns.request;

import java.util.UUID;
import on.logistics.orderservice.global.enums.AuthRole;

public record ReturnRequestRequestDto(
    UUID orderId,
    UUID vendorOrderId,
    UUID userId,
    AuthRole role
) {

    public static ReturnRequestRequestDto of(
        UUID orderId,
        UUID vendorOrderId,
        UUID userId,
        String role
    ) {
        return new ReturnRequestRequestDto(
            orderId,
            vendorOrderId,
            userId,
            AuthRole.valueOf(role)
        );
    }
}
