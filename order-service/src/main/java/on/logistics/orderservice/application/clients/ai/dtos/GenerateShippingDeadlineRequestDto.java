package on.logistics.orderservice.application.clients.ai.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import on.logistics.orderservice.domain.entity.VendorOrder;

public record GenerateShippingDeadlineRequestDto(
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

  public static GenerateShippingDeadlineRequestDto from(VendorOrder createdVendorOrder) {
    return new GenerateShippingDeadlineRequestDto(
        createdVendorOrder.getOrderProducts().stream()
            .map(orderProduct -> new Product(
                orderProduct.getProductId(),
                orderProduct.getQuantity().getValue()
            )).toList(),
        createdVendorOrder.getArrivalDeadline(),
        createdVendorOrder.getVendor().getStartHubId(),
        createdVendorOrder.getOrder().getDestination()
    );
  }
}
