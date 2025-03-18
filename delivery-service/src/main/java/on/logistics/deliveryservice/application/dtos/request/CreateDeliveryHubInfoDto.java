package on.logistics.deliveryservice.application.dtos.request;

import java.util.UUID;

public record CreateDeliveryHubInfoDto(UUID endHubId) {

    public static CreateDeliveryHubInfoDto of(UUID endHubId) {
        return new CreateDeliveryHubInfoDto(endHubId);
    }

}
