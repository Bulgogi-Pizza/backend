package on.logistics.mapservice.presentation.dtos;

import java.util.List;
import lombok.Builder;

@Builder
public record DirectionsResponseDto(
    RouteSummary summary
) {

    @Builder
    public record RouteSummary(
        Integer distance,
        Long duration,
        Start start,
        End end,
        String departureTime,
        String eta,
        Integer tollFare
    ) {

    }

    @Builder
    public record Start(
        List<Double> location
    ) {

    }

    @Builder
    public record End(
        List<Double> location
    ) {

    }

}