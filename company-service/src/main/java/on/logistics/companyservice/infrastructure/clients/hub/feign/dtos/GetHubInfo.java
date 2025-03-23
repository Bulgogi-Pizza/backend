package on.logistics.companyservice.infrastructure.clients.hub.feign.dtos;

import java.util.UUID;

public record GetHubInfo(UUID id, String hubName, String hubType, String address, String latitude,
                         String longitude) {

    public static GetHubInfo getHubInfo(UUID id, String hubName, String hubType, String address,
        String latitude, String longitude) {
        return new GetHubInfo(id, hubName, hubType, address, latitude, longitude);
    }
}
