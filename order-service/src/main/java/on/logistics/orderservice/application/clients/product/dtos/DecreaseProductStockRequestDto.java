package on.logistics.orderservice.application.clients.product.dtos;

import java.util.UUID;
import on.logistics.orderservice.application.dtos.create.CreateOrderRequestDto.OrdersByVendor.OrderedProduct;

public record DecreaseProductStockRequestDto(
    UUID productId,
    Long quantity
) {

  public static DecreaseProductStockRequestDto from(OrderedProduct orderedProduct) {
    return new DecreaseProductStockRequestDto(
        orderedProduct.productId(),
        orderedProduct.quantity()
    );
  }
}
