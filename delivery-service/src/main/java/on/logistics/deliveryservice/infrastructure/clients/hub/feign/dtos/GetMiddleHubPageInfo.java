package on.logistics.deliveryservice.infrastructure.clients.hub.feign.dtos;

import java.util.List;

public record GetMiddleHubPageInfo(List<HubInfo> content, boolean last, int totalPages,
                                   int totalElements) {

}
