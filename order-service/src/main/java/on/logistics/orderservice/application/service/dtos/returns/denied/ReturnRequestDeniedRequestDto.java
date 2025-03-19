package on.logistics.orderservice.application.service.dtos.returns.denied;

import java.util.UUID;

public record ReturnRequestDeniedRequestDto(
    UUID orderId,
    UUID vendorOrderId
) {

  public static ReturnRequestDeniedRequestDto from(UUID orderId, UUID vendorOrderId) {
    return new ReturnRequestDeniedRequestDto(orderId, vendorOrderId);
  }
}
