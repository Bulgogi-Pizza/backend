package on.logistics.orderservice.application.service.dtos.get.detail;

import java.util.UUID;
import on.logistics.orderservice.global.enums.AuthRole;

public record GetOrderDetailRequestDto(
    UUID orderId,
    UUID userId,
    AuthRole role
) {

    public static GetOrderDetailRequestDto of(
        UUID orderId,
        UUID userId,
        String role
    ) {
        return new GetOrderDetailRequestDto(
            orderId,
            userId,
            AuthRole.valueOf(role)
        );
    }
}
