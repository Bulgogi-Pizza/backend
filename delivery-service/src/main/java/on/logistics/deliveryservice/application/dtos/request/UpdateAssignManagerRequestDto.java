package on.logistics.deliveryservice.application.dtos.request;

import java.util.UUID;

public record UpdateAssignManagerRequestDto(UUID deliveryId, UUID companyDeliveryManagerId) {

}
