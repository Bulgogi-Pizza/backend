package on.logistics.orderservice.infrastructure.clients.ai.feign.dtos;

import java.time.LocalDateTime;

public record GenerateShippingDeadlineResponse(
    LocalDateTime shippingDeadline
) {

}
