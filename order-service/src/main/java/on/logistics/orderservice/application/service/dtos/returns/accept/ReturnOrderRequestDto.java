package on.logistics.orderservice.application.service.dtos.returns.accept;

import java.util.UUID;

public record ReturnOrderRequestDto(
    UUID orderId,
    UUID vendorOrderId
) {

  public static ReturnOrderRequestDto of(UUID orderId, UUID vendorOrderId) {
    return new ReturnOrderRequestDto(orderId, vendorOrderId);
  }
}
