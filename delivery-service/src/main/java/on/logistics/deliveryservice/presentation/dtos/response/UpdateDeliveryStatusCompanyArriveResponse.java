package on.logistics.deliveryservice.presentation.dtos.response;

import java.util.UUID;

public record UpdateDeliveryStatusCompanyArriveResponse(UUID deliveryId) {

    public static UpdateDeliveryStatusCompanyArriveResponse of(UUID deliveryId) {
        return new UpdateDeliveryStatusCompanyArriveResponse(deliveryId);
    }

}
