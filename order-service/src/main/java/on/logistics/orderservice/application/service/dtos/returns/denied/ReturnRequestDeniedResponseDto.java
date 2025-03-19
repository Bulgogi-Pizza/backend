package on.logistics.orderservice.application.service.dtos.returns.denied;

import java.util.UUID;
import on.logistics.orderservice.domain.entity.VendorOrder;

public record ReturnRequestDeniedResponseDto(
    UUID vendorOrderId
) {

  public static ReturnRequestDeniedResponseDto from(VendorOrder vendorOrder) {
    return new ReturnRequestDeniedResponseDto(vendorOrder.getId());
  }
}
