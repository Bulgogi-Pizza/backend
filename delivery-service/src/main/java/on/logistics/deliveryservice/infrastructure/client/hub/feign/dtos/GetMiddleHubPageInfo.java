package on.logistics.deliveryservice.infrastructure.client.hub.feign.dtos;

import java.util.List;

public record GetMiddleHubPageInfo(String message, List<HubInfo> data, boolean last,
                                   int totalPages,
                                   int totalElements) {

}
