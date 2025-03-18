package on.logistics.orderservice.application.clients.product.feign.dtos;

import on.logistics.orderservice.application.clients.product.dtos.DecreaseProductStockRequestDto;

public record DecreaseProductStockRequest(
    Long quantity
) {

  public static DecreaseProductStockRequest from(DecreaseProductStockRequestDto requestDto) {
    return new DecreaseProductStockRequest(requestDto.quantity());
  }
}
