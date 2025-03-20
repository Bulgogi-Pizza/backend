package on.logistics.deliveryservice.application.dtos.request;

import java.util.UUID;

public record CreateDeliveryRequestDto(UUID orderId, String destination, UUID startHubId) {

}
