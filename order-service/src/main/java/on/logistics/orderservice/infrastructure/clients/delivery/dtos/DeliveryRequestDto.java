package on.logistics.orderservice.infrastructure.clients.delivery.dtos;

import java.util.UUID;
import on.logistics.orderservice.domain.entity.VendorOrder;

public record DeliveryRequestDto(
  UUID orderId,
  String destination,
  UUID startHubId
) {

  public static DeliveryRequestDto from(VendorOrder vendorOrder) {
    return new DeliveryRequestDto(
        vendorOrder.getId(),
        vendorOrder.getOrder().getDestination(),
        vendorOrder.getVendor().getStartHubId()
    );
  }
}
