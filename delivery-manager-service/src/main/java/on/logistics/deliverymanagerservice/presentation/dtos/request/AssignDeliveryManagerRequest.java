package on.logistics.deliverymanagerservice.presentation.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record AssignDeliveryManagerRequest(
    @NotBlank String deliveryId,
    @NotBlank String hubId,
    @NotBlank String deliveryType
) {

}
