package on.logistics.orderservice.presentation.dtos.create;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CreateOrderRequest(
    @NotNull UUID ordererId,
    @NotBlank String ordererName,
    @NotBlank String destination,
    @NotNull @PositiveOrZero Long totalAmount,
    @NotNull List<OrdersByVendor> ordersByVendor
) {

  public record OrdersByVendor(
      @NotNull UUID vendorId,
      @NotBlank String vendorName,
      @NotNull UUID vendorHubId,
      @NotBlank String vendorHubName,
      @NotNull @PositiveOrZero Long totalAmount,
      @NotNull @Future LocalDateTime arrivalDeadline,
      @NotNull List<OrderedProduct> orderedProducts
  ) {

    public record OrderedProduct(
        @NotNull UUID productId,
        @NotBlank String name,
        @NotNull Long quantity,
        @NotNull Long price
    ) {

    }
  }


}
