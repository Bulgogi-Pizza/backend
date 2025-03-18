package on.logistics.deliveryservice.application.dtos;

import java.util.UUID;

public record TypeHubInfoDto(UUID hubId, String hubName, String hubType, String hubAddress,
                             String latitude, String longitude) {

}
