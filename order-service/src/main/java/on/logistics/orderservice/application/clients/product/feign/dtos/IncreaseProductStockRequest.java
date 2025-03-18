package on.logistics.orderservice.application.clients.product.feign.dtos;

import java.util.UUID;
import on.logistics.orderservice.application.clients.product.dtos.RollbackDecreaseProductStockRequestDto;

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
