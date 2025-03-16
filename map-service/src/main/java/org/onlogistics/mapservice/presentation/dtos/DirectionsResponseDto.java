package org.onlogistics.mapservice.presentation.dtos;

import java.util.List;
import lombok.Builder;

@Builder
public record DirectionsResponseDto(
    RouteSummary summary,
    List<List<Double>> path
) {

    @Builder
    public record RouteSummary(
        Integer distance,
        Long duration,
        Start start,
        End end,
        String departureTime,
        String eta
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