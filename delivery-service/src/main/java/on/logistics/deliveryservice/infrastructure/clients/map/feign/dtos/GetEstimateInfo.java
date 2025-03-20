package on.logistics.deliveryservice.infrastructure.clients.map.feign.dtos;

import java.util.List;

public record GetEstimateInfo(Summary summary) {

    public record Summary(long distance, long duration, GetHubRouteInfo.Location start,
                          GetHubRouteInfo.Location end, String departureTime, String eta,
                          int tollFare) {

    }

    public record Location(List<Double> location) {

    }

}
