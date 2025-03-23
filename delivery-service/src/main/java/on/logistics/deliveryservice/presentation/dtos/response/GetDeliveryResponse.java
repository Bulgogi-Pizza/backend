package on.logistics.deliveryservice.presentation.dtos.response;

import java.util.UUID;
import on.logistics.deliveryservice.domain.entity.Delivery;
import on.logistics.deliveryservice.domain.enums.DeliveryStatus;

public record GetDeliveryResponse(UUID deliveryId, DeliveryStatus status, UUID orderId,
                                  UUID startHubId, UUID endHubId, String description,
                                  String recipient, String recipientSlackEmail,
                                  UUID userId) {

    public static GetDeliveryResponse from(Delivery delivery) {
        return new GetDeliveryResponse(delivery.getId(), delivery.getStatus(),
            delivery.getOrderId(), delivery.getStartHubId(), delivery.getEndHubId(),
            delivery.getDestination().getValue(), delivery.getRecipient().getValue(),
            delivery.getRecipientSlackEmail().getValue(), delivery.getUserId());
    }

}
