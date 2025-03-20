package on.logistics.orderservice.domain.entity.dtos;

import java.util.UUID;
import on.logistics.orderservice.application.service.dtos.create.CreateOrderRequestDto.OrdersByVendor.OrderedProduct;
import on.logistics.orderservice.domain.entity.VendorOrder;

public record CreateOrderProductDto(
    VendorOrder vendorOrder,
    UUID productId,
    Long quantity,
    Long price,
    String name
) {

    public static CreateOrderProductDto of(
        VendorOrder vendorOrder,
        OrderedProduct orderedProduct
    ) {
        return new CreateOrderProductDto(
            vendorOrder,
            orderedProduct.productId(),
            orderedProduct.quantity(),
            orderedProduct.price(),
            orderedProduct.name()
        );
    }
}
