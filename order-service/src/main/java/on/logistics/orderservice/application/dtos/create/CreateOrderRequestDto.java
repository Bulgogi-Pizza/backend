package on.logistics.orderservice.application.dtos.create;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import on.logistics.orderservice.presentation.dtos.create.CreateOrderRequest;
import org.springframework.lang.Nullable;

public record CreateOrderRequestDto(
    UUID OrdererId,
    String destination,
    Long totalAmount,
    List<OrdersByVendor> ordersByVendor
) {

  public record OrdersByVendor(
      UUID vendorId,
      Long totalAmount,
      LocalDateTime arrivalDeadline,
      List<OrderedProduct> orderItems
  ) {

    public record OrderedProduct(
        UUID productId,
        Long quantity,
        Long price
    ) {

    }
  }

  public static CreateOrderRequestDto from(CreateOrderRequest createOrderRequest) {
    return new CreateOrderRequestDto(
        createOrderRequest.ordererId(),
        createOrderRequest.destination(),
        createOrderRequest.totalAmount(),
        createOrderRequest.ordersByVendor().stream()
            .map(ordersByVendor -> new OrdersByVendor(
                ordersByVendor.vendorId(),
                ordersByVendor.totalAmount(),
                ordersByVendor.arrivalDeadline(),
                ordersByVendor.orderedProducts().stream()
                    .map(orderedProduct -> new OrdersByVendor.OrderedProduct(
                        orderedProduct.productId(),
                        orderedProduct.quantity(),
                        orderedProduct.price()
                    ))
                    .toList()
            ))
            .toList()
    );
  }
}
