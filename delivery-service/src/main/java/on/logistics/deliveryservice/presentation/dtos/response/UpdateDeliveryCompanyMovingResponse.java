package on.logistics.deliveryservice.presentation.dtos.response;

import java.util.UUID;

public record UpdateDeliveryCompanyMovingResponse(UUID deliveryId) {

    public static UpdateDeliveryCompanyMovingResponse of(UUID deliveryId) {
        return new UpdateDeliveryCompanyMovingResponse(deliveryId);
    }

}
