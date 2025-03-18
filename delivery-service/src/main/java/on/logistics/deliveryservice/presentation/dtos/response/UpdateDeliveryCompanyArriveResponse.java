package on.logistics.deliveryservice.presentation.dtos.response;

import java.util.UUID;

public record UpdateDeliveryCompanyArriveResponse(UUID deliveryId) {

    public static UpdateDeliveryCompanyArriveResponse of(UUID deliveryId) {
        return new UpdateDeliveryCompanyArriveResponse(deliveryId);
    }

}
