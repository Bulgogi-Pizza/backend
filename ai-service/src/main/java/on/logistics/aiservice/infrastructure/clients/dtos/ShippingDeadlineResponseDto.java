package on.logistics.aiservice.infrastructure.clients.dtos;

import java.time.LocalDateTime;

public record ShippingDeadlineResponseDto(
    LocalDateTime shippingDeadline
) {

    public static ShippingDeadlineResponseDto from(LocalDateTime shippingDeadline) {
        return new ShippingDeadlineResponseDto(shippingDeadline);
    }
}
