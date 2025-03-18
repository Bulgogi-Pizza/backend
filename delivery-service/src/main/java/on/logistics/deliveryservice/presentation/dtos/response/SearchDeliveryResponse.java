package on.logistics.deliveryservice.presentation.dtos.response;

import java.util.UUID;
import on.logistics.deliveryservice.domain.Delivery;
import on.logistics.deliveryservice.domain.enums.DeliveryStatus;

public record SearchDeliveryResponse(UUID deliveryId, DeliveryStatus deliveryStatus,
                                     String description, String recipient,
                                     UUID companyDeliveryManagerId) {

    public static SearchDeliveryResponse from(Delivery delivery) {
        return new SearchDeliveryResponse(delivery.getId(), delivery.getStatus(),
            delivery.getDestination().getValue(), delivery.getRecipient().getValue(),
            delivery.getCompanyDeliveryManagerId());
    }
}