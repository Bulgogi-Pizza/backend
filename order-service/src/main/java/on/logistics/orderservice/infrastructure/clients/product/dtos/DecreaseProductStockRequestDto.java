package on.logistics.orderservice.infrastructure.clients.product.dtos;

import java.util.UUID;
import on.logistics.orderservice.domain.entity.OrderProduct;

public record DecreaseProductStockRequestDto(
    UUID productId,
    Long quantity
) {

    public static DecreaseProductStockRequestDto from(OrderProduct orderProduct) {
        return new DecreaseProductStockRequestDto(
            orderProduct.getProductId(),
            orderProduct.getQuantity().getValue()
        );
    }
}
