package on.logistics.hubservice.infrastructure.clients.deliverymanager.feign.dtos.response;

import java.util.UUID;

public record AssignDeliveryManagerResponse(
    UUID deliveryManagerId
) {

}
