package on.logistics.deliveryservice.presentation.dtos.request;

import java.util.UUID;

public record CreateDeliveryRecordRequest(UUID deliveryId, UUID deliveryRecordStartHubId,
                                          UUID deliveryRecordEndHubId, UUID deliveryManagerId) {

}