package on.logistics.orderservice.presentation.dtos.delete;

import java.util.UUID;

public record DeleteOrderRequestDto(
    UUID orderId,
    UUID vendorOrderId
) {

    public static DeleteOrderRequestDto of(UUID orderId, UUID vendorOrderId) {
        return new DeleteOrderRequestDto(
            orderId,
            vendorOrderId
        );
    }
}
