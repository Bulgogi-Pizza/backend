package on.logistics.hubtransitservice.presentation.dtos.response;

import java.util.UUID;
import lombok.Builder;
import on.logistics.hubtransitservice.domain.entity.HubTransit;
import on.logistics.hubtransitservice.domain.enums.DeliveryType;

@Builder
public record GetNextHubResponse(
    UUID transitId,
    UUID deliveryId,
    UUID deliveryRecordId,
    UUID nextHubId,
    String nextHubName,
    DeliveryType nextDeliveryType,
    UUID userId
) {

    public static GetNextHubResponse from(HubTransit hubTransit) {
        return GetNextHubResponse.builder()
            .transitId(hubTransit.getId())
            .deliveryId(hubTransit.getDeliveryId())
            .deliveryRecordId(hubTransit.getDeliveryRecordId())
            .nextHubId(hubTransit.getNextHubId())
            .nextHubName(hubTransit.getNextHubName().getValue())
            .nextDeliveryType(hubTransit.getNextDeliveryType())
            .userId(hubTransit.getUserId())
            .build();
    }

}
