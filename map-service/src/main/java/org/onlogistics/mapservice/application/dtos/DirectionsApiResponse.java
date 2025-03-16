package org.onlogistics.mapservice.application.dtos;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DirectionsApiResponse {

    private Integer code;
    private String message;
    private String currentDateTime;
    private RouteWrapper route;

    @Data
    @NoArgsConstructor
    public static class RouteWrapper {

        private List<RouteInfo> routeInfo;

        @JsonAnySetter
        public void setDynamicRoute(List<RouteInfo> value) {
            if (this.routeInfo == null) {
                this.routeInfo = value;
            }
        }
    }

    @Data
    @NoArgsConstructor
    public static class RouteInfo {

        private Summary summary;
        private List<List<Double>> path;
    }

    @Data
    @NoArgsConstructor
    public static class Summary {

        private Start start;
        private Goal goal;
        private Integer distance;
        private Long duration;
        private String departureTime;
    }

    @Data
    @NoArgsConstructor
    public static class Start {

        private List<Double> location;
    }

    @Data
    @NoArgsConstructor
    public static class Goal {

        private List<Double> location;
        private Integer dir;
    }

}
