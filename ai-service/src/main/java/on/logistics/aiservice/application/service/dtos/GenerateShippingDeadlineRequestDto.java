package on.logistics.aiservice.application.service.dtos;

import java.time.LocalDateTime;
import java.util.List;
import on.logistics.aiservice.presentation.dtos.GenerateShippingDeadlineRequest;

public record GenerateShippingDeadlineRequestDto(
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

    public static GenerateShippingDeadlineRequestDto from(GenerateShippingDeadlineRequest request) {
        return new GenerateShippingDeadlineRequestDto(
            request.products().stream()
                .map(product -> new Product(product.name(), product.quantity()))
                .toList(),
            request.arrivalDeadline(),
            request.startHubName(),
            request.endHubName(),
            request.destination()
        );
    }
}
