package on.logistics.orderservice.presentation.dtos.update;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record UpdateOrderRequest(
    @NotEmpty List<OrdersByVendor> ordersByVendor
) {

  public record OrdersByVendor(
      @NotNull UUID orderIdByVendor,
      @NotNull @Future LocalDateTime arrivalDeadline,
      @NotEmpty List<OrderedProduct> orderedProducts
  ) {

    public record OrderedProduct(
        @NotNull UUID productId,
        @NotNull @PositiveOrZero Long quantity
    ) {

    }
  }

}
