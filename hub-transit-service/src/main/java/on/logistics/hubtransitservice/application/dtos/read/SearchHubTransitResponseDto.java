package on.logistics.hubtransitservice.application.dtos.read;

import java.util.UUID;
import on.logistics.hubtransitservice.domain.entity.HubTransit;

public record SearchHubTransitResponseDto(
    UUID transitId,
    String currentHubName,
    String nextHubName,
    UUID deliveryId,
    UUID deliveryManagerId
) {

    public static SearchHubTransitResponseDto from(HubTransit hubTransit) {
        return new SearchHubTransitResponseDto(
            hubTransit.getId(),
            hubTransit.getCurrentHubName().getValue(),
            hubTransit.getNextHubName().getValue(),
            hubTransit.getDeliveryId(),
            hubTransit.getDeliveryManagerId()
        );
    }

}
