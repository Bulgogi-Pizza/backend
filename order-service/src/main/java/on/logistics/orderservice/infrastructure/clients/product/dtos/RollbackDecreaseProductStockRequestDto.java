package on.logistics.orderservice.infrastructure.clients.product.dtos;

import java.util.UUID;
import on.logistics.orderservice.domain.entity.OrderProduct;

public record RollbackDecreaseProductStockRequestDto(
    UUID productId,
    Long quantity
) {

  public static RollbackDecreaseProductStockRequestDto from(OrderProduct orderProduct) {
    return new RollbackDecreaseProductStockRequestDto(
        orderProduct.getProductId(),
        orderProduct.getQuantity().getValue()
    );
  }
}
