package on.logistics.orderservice.infrastructure.clients.product.feign.dtos;

import on.logistics.orderservice.infrastructure.clients.product.dtos.DecreaseProductStockRequestDto;

public record DecreaseProductStockRequest(
    Long productQuantity
) {

    public static DecreaseProductStockRequest from(DecreaseProductStockRequestDto requestDto) {
        return new DecreaseProductStockRequest(requestDto.quantity());
    }
}
