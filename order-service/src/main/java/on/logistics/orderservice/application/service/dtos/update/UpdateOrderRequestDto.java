package on.logistics.orderservice.application.service.dtos.update;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import on.logistics.orderservice.presentation.dtos.update.UpdateOrderRequest;

public record UpdateOrderRequestDto(
    UUID orderId,
    List<OrdersByVendor> ordersByVendor
) {

  public record OrdersByVendor(
      UUID orderIdByVendor,
      LocalDateTime arrivalDeadline,
      List<OrderedProduct> orderedProducts
  ) {

    public record OrderedProduct(
        UUID productId,
        Long quantity
    ) {

    }
  }

  public static UpdateOrderRequestDto of(UpdateOrderRequest request, UUID orderId) {
    return new UpdateOrderRequestDto(
        orderId,
        request.ordersByVendor().stream()
            .map(ordersByVendor -> new OrdersByVendor(
                ordersByVendor.orderIdByVendor(),
                ordersByVendor.arrivalDeadline(),
                ordersByVendor.orderedProducts().stream()
                    .map(orderedProduct -> new OrdersByVendor.OrderedProduct(
                        orderedProduct.productId(),
                        orderedProduct.quantity()
                    ))
                    .toList()
            ))
            .toList()
    );
  }
}
