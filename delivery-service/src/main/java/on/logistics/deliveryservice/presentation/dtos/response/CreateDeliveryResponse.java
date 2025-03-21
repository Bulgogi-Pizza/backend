package on.logistics.deliveryservice.presentation.dtos.response;

import java.util.UUID;

public record CreateDeliveryResponse(UUID deliveryId) {

    public static CreateDeliveryResponse of(UUID deliveryId) {
        return new CreateDeliveryResponse(deliveryId);
    }
}
