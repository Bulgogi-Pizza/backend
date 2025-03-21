package on.logistics.orderservice.application.service.dtos.get.detail;

import java.util.UUID;

public record GetOrderDetailRequestDto(
    UUID orderId
) {

    public static GetOrderDetailRequestDto from(UUID orderId) {
        return new GetOrderDetailRequestDto(orderId);
    }
}
