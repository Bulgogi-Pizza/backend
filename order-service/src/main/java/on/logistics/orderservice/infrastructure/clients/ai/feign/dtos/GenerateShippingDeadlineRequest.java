package on.logistics.orderservice.infrastructure.clients.ai.feign.dtos;

import java.time.LocalDateTime;
import java.util.List;
import on.logistics.orderservice.infrastructure.clients.ai.dtos.GenerateShippingDeadlineRequestDto;

public record GenerateShippingDeadlineRequest(
    List<Product> products,
    LocalDateTime arrivalDeadline,
    String startHubName,
    String endHubName,
    String destination
) {

    public record Product(
        String name,
        Long quantity
    ) {

    }

    public static GenerateShippingDeadlineRequest from(
        GenerateShippingDeadlineRequestDto requestDto
    ) {
        return new GenerateShippingDeadlineRequest(
            requestDto.products().stream()
                .map(product -> new Product(
                    product.name(),
                    product.quantity()
                )).toList(),
            requestDto.arrivalDeadline(),
            requestDto.startHubName(),
            requestDto.endHubName(),
            requestDto.destination()
        );
    }
}
