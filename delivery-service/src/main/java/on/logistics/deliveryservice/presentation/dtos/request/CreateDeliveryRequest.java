package on.logistics.deliveryservice.presentation.dtos.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import on.logistics.deliveryservice.application.dtos.request.CreateDeliveryRequestDto;

public record CreateDeliveryRequest(@NotNull(message = "주문 ID는 필수 값입니다.") UUID orderId,
                                    @NotNull(message = "목적지는 필수 입력 값입니다.") String description,
                                    @NotNull(message = "스타트 허브는 필수 입력 값입니다.") UUID startHubId) {

    public static CreateDeliveryRequestDto from(CreateDeliveryRequest createDeliveryRequest) {
        return new CreateDeliveryRequestDto(createDeliveryRequest.orderId,
            createDeliveryRequest.description(), createDeliveryRequest.startHubId());
    }
}
