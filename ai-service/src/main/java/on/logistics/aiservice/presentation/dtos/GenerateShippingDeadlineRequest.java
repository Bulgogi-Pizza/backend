package on.logistics.aiservice.presentation.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public record GenerateShippingDeadlineRequest(
    @NotEmpty List<Product> products,
    @NotNull @Future LocalDateTime arrivalDeadline,
    @NotBlank String startHubName,
    @NotBlank String endHubName,
    @NotBlank String destination
) {

    public record Product(
        @NotBlank String name,
        @NotNull @Min(1) Long quantity
    ) {

    }
}
