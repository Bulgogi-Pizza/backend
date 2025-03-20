package on.logistics.orderservice.application.service.dtos.create;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import on.logistics.orderservice.presentation.dtos.create.CreateOrderRequest;

public record CreateOrderRequestDto(
    UUID OrdererId,
    String ordererName,
    UUID ordererUserId,
    String ordererUserNickname,
    UUID ordererHubId,
    String ordererHubName,
    String destination,
    Long totalAmount,
    List<OrdersByVendor> ordersByVendor
) {

  public record OrdersByVendor(
      UUID vendorId,
      String vendorName,
      UUID vendorHubId,
      String vendorHubName,
      Long totalAmount,
      LocalDateTime arrivalDeadline,
      List<OrderedProduct> orderItems
  ) {

    public record OrderedProduct(
        UUID productId,
        String name,
        Long quantity,
        Long price
    ) {

    }
  }

  public static CreateOrderRequestDto of(
      CreateOrderRequest createOrderRequest,
      UUID ordererUserId,
      String ordererUserNickname
  ) {
    return new CreateOrderRequestDto(
        createOrderRequest.ordererId(),
        createOrderRequest.ordererName(),
        ordererUserId,
        ordererUserNickname,
        createOrderRequest.ordererHubId(),
        createOrderRequest.ordererHubName(),
        createOrderRequest.destination(),
        createOrderRequest.totalAmount(),
        createOrderRequest.ordersByVendor().stream()
            .map(ordersByVendor -> new OrdersByVendor(
                ordersByVendor.vendorId(),
                ordersByVendor.vendorName(),
                ordersByVendor.vendorHubId(),
                ordersByVendor.vendorHubName(),
                ordersByVendor.totalAmount(),
                ordersByVendor.arrivalDeadline(),
                ordersByVendor.orderedProducts().stream()
                    .map(orderedProduct -> new OrdersByVendor.OrderedProduct(
                        orderedProduct.productId(),
                        orderedProduct.name(),
                        orderedProduct.quantity(),
                        orderedProduct.price()
                    ))
                    .toList()
            ))
            .toList()
    );
  }
}
