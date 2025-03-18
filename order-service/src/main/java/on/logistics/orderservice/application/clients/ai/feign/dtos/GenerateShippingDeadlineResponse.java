package on.logistics.orderservice.application.clients.ai.feign.dtos;

import java.time.LocalDateTime;

public record GenerateShippingDeadlineResponse(
    LocalDateTime shippingDeadline
) {

}
