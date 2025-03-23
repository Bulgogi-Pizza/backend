package on.logistics.deliveryservice.presentation.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateDeliveryRequest(@NotBlank(message = "목적지는 필수로 입력되어야 합니다.") String destination) {

}
