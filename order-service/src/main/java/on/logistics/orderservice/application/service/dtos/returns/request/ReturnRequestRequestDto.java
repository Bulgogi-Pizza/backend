package on.logistics.orderservice.application.service.dtos.returns.request;

import java.util.UUID;

public record ReturnRequestRequestDto(
    UUID orderId,
    UUID vendorOrderId
) {

  public static ReturnRequestRequestDto of(UUID orderId, UUID vendorOrderId) {
    return new ReturnRequestRequestDto(orderId, vendorOrderId);
  }
}
