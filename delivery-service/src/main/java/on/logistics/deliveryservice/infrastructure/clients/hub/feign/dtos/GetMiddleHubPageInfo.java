package on.logistics.deliveryservice.infrastructure.clients.hub.feign.dtos;

import java.util.List;

public record GetMiddleHubPageInfo(String message, List<HubInfo> data, boolean last,
                                   int totalPages,
                                   int totalElements) {

}
