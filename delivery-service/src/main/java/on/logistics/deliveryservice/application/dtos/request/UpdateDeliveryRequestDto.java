package on.logistics.deliveryservice.application.dtos.request;

import java.util.UUID;

public record UpdateDeliveryRequestDto(UUID deliveryId, UUID orderId, String description) {

}
