package on.logistics.orderservice.infrastructure.clients.product.feign.dtos;

import on.logistics.orderservice.infrastructure.clients.product.dtos.RollbackDecreaseProductStockRequestDto;

public record IncreaseProductStockRequest(
    Long productQuantity
) {

  public static IncreaseProductStockRequest from(
      RollbackDecreaseProductStockRequestDto requestDto
  ) {
    return new IncreaseProductStockRequest(
        requestDto.quantity()
    );
  }
}
