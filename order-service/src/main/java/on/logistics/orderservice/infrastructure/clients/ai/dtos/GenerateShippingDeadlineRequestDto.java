package on.logistics.orderservice.infrastructure.clients.ai.dtos;

import java.time.LocalDateTime;
import java.util.List;
import on.logistics.orderservice.domain.entity.VendorOrder;

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

  public static GenerateShippingDeadlineRequestDto from(VendorOrder createdVendorOrder) {
    return new GenerateShippingDeadlineRequestDto(
        createdVendorOrder.getOrderProducts().stream()
            .map(orderProduct -> new Product(
                orderProduct.getName().getValue(),
                orderProduct.getQuantity().getValue()
            )).toList(),
        createdVendorOrder.getArrivalDeadline(),
        createdVendorOrder.getVendor().getVendorHubName().getValue(),
        createdVendorOrder.getOrder().getOrderer().getOrdererHubName().getValue(),
        createdVendorOrder.getOrder().getDestination()
    );
  }
}
