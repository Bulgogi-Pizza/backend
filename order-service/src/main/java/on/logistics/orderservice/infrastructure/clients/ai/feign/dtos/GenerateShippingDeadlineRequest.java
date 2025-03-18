package on.logistics.orderservice.infrastructure.clients.ai.feign.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import on.logistics.orderservice.infrastructure.clients.ai.dtos.GenerateShippingDeadlineRequestDto;

public record GenerateShippingDeadlineRequest(
    List<Product> products,
    LocalDateTime arrivalDeadline,
    UUID startHubId,
    String destination
) {

  public record Product(
      UUID productId,
      Long quantity
  ) {

  }

  public static GenerateShippingDeadlineRequest from(
      GenerateShippingDeadlineRequestDto requestDto
  ) {
    return new GenerateShippingDeadlineRequest(
        requestDto.products().stream()
            .map(product -> new Product(
                product.productId(),
                product.quantity()
            )).toList(),
        requestDto.arrivalDeadline(),
        requestDto.startHubId(),
        requestDto.destination()
    );
  }
}
