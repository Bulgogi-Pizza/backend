package on.logistics.hubtransitservice.application.dtos.create;

import java.util.UUID;

public record CreateHubTransitRequestDto(
    UUID startHubId,
    UUID endHubId,
    UUID deliveryId,
    String startHubName,
    String endHubName,
    UUID deliveryManagerId
) {

    public static CreateHubTransitRequestDto of(
        UUID startHubId,
        UUID endHubId,
        UUID deliveryId,
        String startHubName,
        String endHubName,
        UUID deliveryManagerId
    ) {
        return new CreateHubTransitRequestDto(
            startHubId,
            endHubId,
            deliveryId,
            startHubName,
            endHubName,
            deliveryManagerId
        );
    }

    public CreateHubTransitRequestDto withHubNameAndManager(String startHubName,
        String endHubName, UUID deliveryManagerId
    ) {
        return new CreateHubTransitRequestDto(
            this.startHubId,
            this.endHubId,
            this.deliveryId,
            startHubName,
            endHubName,
            deliveryManagerId
        );
    }

}
