package org.onlogistics.mapservice.application.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.onlogistics.mapservice.application.dtos.DirectionsApiResponse;
import org.onlogistics.mapservice.application.dtos.DirectionsApiResponse.RouteInfo;
import org.onlogistics.mapservice.exception.MapException;
import org.onlogistics.mapservice.presentation.dtos.DirectionsResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j(topic = "Directions Service")
@Service
@RequiredArgsConstructor
public class DirectionsServiceImpl implements DirectionsService {

    private final WebClient webClient;

    @Value("${naver.map.api.url}")
    private String baseUrl;

    @Value("${naver.map.api.key}")
    private String apiKey;

    @Value("${naver.map.api.secret}")
    private String apiSecret;

    @Override
    public DirectionsResponseDto getRoute(String start, String end, String option, int cartype) {
        String url = buildRequestUrl(start, end, option, cartype);
        try {
            DirectionsApiResponse directionsApiResponse = getDirectionResponse(url);
            if (!hasValidRouteInfo(directionsApiResponse)) {
                log.error("잘못된 경로 응답: url=[{}], response=[{}]", url, directionsApiResponse);
                throw new MapException.InvalidResponseException();
            }
            RouteInfo routeInfo = getRouteInfo(directionsApiResponse);
            return mapToDto(routeInfo);
        } catch (MapException.InvalidResponseException e) {
            log.error("경로 응답 오류: url=[{}], error=[{}]", url, e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("경로 검색 중 알 수 없는 오류 발생: url=[{}], error=[{}]", url, e.getMessage());
            throw new MapException.ExternalApiCallException();
        }
    }

    private String buildRequestUrl(String start, String end, String option, int cartype) {
        return String.format(
            "%s/map-direction/v1/driving?start=%s&goal=%s&option=%s&cartype=%d",
            baseUrl, start, end, option, cartype);
    }

    private DirectionsApiResponse getDirectionResponse(String url) {
        return webClient.get()
            .uri(url)
            .header("x-ncp-apigw-api-key-id", apiKey)
            .header("x-ncp-apigw-api-key", apiSecret)
            .retrieve()
            .bodyToMono(DirectionsApiResponse.class)
            .block();
    }

    private boolean hasValidRouteInfo(DirectionsApiResponse apiResponse) {
        return apiResponse != null &&
            apiResponse.getRoute() != null &&
            apiResponse.getRoute().getRouteInfo() != null &&
            !apiResponse.getRoute().getRouteInfo().isEmpty();
    }

    private RouteInfo getRouteInfo(DirectionsApiResponse directionsApiResponse) {
        return directionsApiResponse.getRoute().getRouteInfo().get(0);
    }

    private DirectionsResponseDto mapToDto(DirectionsApiResponse.RouteInfo routeInfo) {
        DirectionsApiResponse.Summary summary = routeInfo.getSummary();

        LocalDateTime departure = LocalDateTime.parse(summary.getDepartureTime(),
            DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime eta = departure.plus(Duration.ofMillis(summary.getDuration()));

        DirectionsResponseDto.RouteSummary routeSummary = DirectionsResponseDto.RouteSummary.builder()
            .distance(summary.getDistance())
            .duration(summary.getDuration())
            .start(DirectionsResponseDto.Start.builder()
                .location(summary.getStart().getLocation())
                .build())
            .end(DirectionsResponseDto.End.builder()
                .location(summary.getGoal().getLocation())
                .build())
            .departureTime(summary.getDepartureTime())
            .eta(eta.format(DateTimeFormatter.ISO_DATE_TIME))
            .build();

        List<List<Double>> path = routeInfo.getPath();

        return DirectionsResponseDto.builder()
            .summary(routeSummary)
            .path(path)
            .build();
    }
}
