package on.logistics.deliveryservice.application.dtos;

import java.util.UUID;

public record DeliveryHubInfoDto(UUID endHubId) {

    public static DeliveryHubInfoDto of(UUID endHubId) {
        return new DeliveryHubInfoDto(endHubId);
    }

}
