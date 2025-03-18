package on.logistics.orderservice.infrastructure.clients.product.feign.dtos;

import java.util.UUID;
import on.logistics.orderservice.infrastructure.clients.product.dtos.RollbackDecreaseProductStockRequestDto;

public record IncreaseProductStockRequest(
    UUID productId,
    Long quantity
) {

  public static IncreaseProductStockRequest from(
      RollbackDecreaseProductStockRequestDto requestDto
  ) {
    return new IncreaseProductStockRequest(
        requestDto.productId(),
        requestDto.quantity()
    );
  }
}
