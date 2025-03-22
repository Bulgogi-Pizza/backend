package on.logistics.orderservice.presentation.dtos.delete;

import java.util.UUID;
import on.logistics.orderservice.global.enums.AuthRole;

public record DeleteOrderRequestDto(
    UUID orderId,
    UUID vendorOrderId,
    UUID userId,
    AuthRole role
) {

    public static DeleteOrderRequestDto of(
        UUID orderId,
        UUID vendorOrderId,
        UUID userId,
        String role
    ) {
        return new DeleteOrderRequestDto(
            orderId,
            vendorOrderId,
            userId,
            AuthRole.valueOf(role)
        );
    }
}
