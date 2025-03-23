package on.logistics.deliveryservice.infrastructure.clients.hub.feign.dtos;

import java.util.List;

public record GetSpokeHubInfo(List<HubInfo> data) {

    public static GetSpokeHubInfo from(List<HubInfo> data) {
        return new GetSpokeHubInfo(data);
    }
}