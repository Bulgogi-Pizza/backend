package on.logistics.deliveryservice.presentation.dtos.response;

import java.util.UUID;

public record UpdateDeliveryStatusCompanyMovingResponse(UUID deliveryId) {

    public static UpdateDeliveryStatusCompanyMovingResponse of(UUID deliveryId) {
        return new UpdateDeliveryStatusCompanyMovingResponse(deliveryId);
    }

}
