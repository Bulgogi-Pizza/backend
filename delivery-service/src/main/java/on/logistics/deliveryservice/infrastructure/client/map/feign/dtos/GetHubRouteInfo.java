package on.logistics.deliveryservice.infrastructure.client.map.feign.dtos;

import java.util.List;

public record GetHubRouteInfo(Summary summary) {

    public record Summary(
        int distance,
        long duration,
        Location start,
        Location end,
        String departureTime,
        String eta,
        int tollFare
    ) {

    }

    public record Location(List<Double> location) {

    }
}
