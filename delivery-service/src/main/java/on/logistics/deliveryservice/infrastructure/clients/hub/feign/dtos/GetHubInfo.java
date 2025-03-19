package on.logistics.deliveryservice.infrastructure.clients.hub.feign.dtos;

import java.util.UUID;

public record GetHubInfo(UUID id, String name, String address, String latitude, String longitude) {

    public static GetHubInfo getHubInfo(UUID id, String name, String address, String latitude,
        String longitude) {
        return new GetHubInfo(id, name, address, latitude, longitude);
    }
}
