package on.logistics.deliveryservice.presentation.dtos.request;

import java.util.UUID;

public record UpdateDeliveryRequest(UUID deliveryId, String destination) {

}
