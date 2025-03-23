package on.logistics.hubtransitservice.presentation.dtos.response;

import java.util.UUID;
import lombok.Builder;
import on.logistics.hubtransitservice.domain.entity.HubTransit;
import on.logistics.hubtransitservice.domain.enums.DeliveryType;

@Builder
public record GetHubTransitResponse(
    UUID transitId,
    UUID deliveryId,
    UUID deliveryRecordId,
    UUID currentHubId,
    String currentHubName,
    UUID nextHubId,
    String nextHubName,
    DeliveryType nextDeliveryType,
    UUID userId,
    String route
) {

    public static GetHubTransitResponse from(HubTransit hubTransit) {
        return GetHubTransitResponse.builder()
            .transitId(hubTransit.getId())
            .deliveryId(hubTransit.getDeliveryId())
            .deliveryRecordId(hubTransit.getDeliveryRecordId())
            .currentHubId(hubTransit.getCurrentHubId())
            .currentHubName(hubTransit.getCurrentHubName().getValue())
            .nextHubId(hubTransit.getNextHubId())
            .nextHubName(hubTransit.getNextHubName().getValue())
            .nextDeliveryType(hubTransit.getNextDeliveryType())
            .userId(hubTransit.getUserId())
            .route(hubTransit.getRouteSnapshot())
            .build();
    }
}
