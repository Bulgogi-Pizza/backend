package org.onlogistics.mapservice.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.onlogistics.mapservice.application.dtos.GeocodingApiResponse;
import org.onlogistics.mapservice.exception.MapException;
import org.onlogistics.mapservice.presentation.dtos.GeocodingResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j(topic = "Geocoding Service")
@Service
@RequiredArgsConstructor
public class GeocodingServiceImpl implements GeocodingService {

    private final WebClient webClient;

    @Value("${naver.map.api.url}")
    private String baseUrl;

    @Value("${naver.map.api.key}")
    private String apiKey;

    @Value("${naver.map.api.secret}")
    private String apiSecret;

    @Override
    public GeocodingResponseDto getCoordinates(String query) {
        String url = buildRequestUrl(query);
        try {
            GeocodingApiResponse apiResponse = getGeocodeResponse(url);
            GeocodingApiResponse.Address address = extractAddress(apiResponse);
            return convertToResponse(address);
        } catch (MapException.InvalidResponseException e) {
            log.error("API 응답 오류. query: {}. 응답: {}", query, e.getMessage());
            throw e;
        } catch (MapException.ExternalApiCallException e) {
            log.error("외부 API 호출 중 오류 발생. query: {}", query, e);
            throw e;
        } catch (Exception e) {
            log.error("좌표 변환 중 오류 발생. query: {}", query, e);
            throw new MapException.ExternalApiCallException();
        }
    }

    private String buildRequestUrl(String query) {
        return baseUrl + "/map-geocode/v2/geocode?query=" + query;
    }

    private GeocodingApiResponse getGeocodeResponse(String url) {
        return webClient.get()
            .uri(url)
            .header("x-ncp-apigw-api-key-id", apiKey)
            .header("x-ncp-apigw-api-key", apiSecret)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(GeocodingApiResponse.class)
            .block();
    }

    private GeocodingApiResponse.Address extractAddress(GeocodingApiResponse response) {
        if (response == null || response.getAddresses() == null || response.getAddresses()
            .isEmpty()) {
            log.error("API 응답이 올바르지 않습니다. apiResponse: {}", response);
            throw new MapException.InvalidResponseException();
        }
        return response.getAddresses().get(0);
    }

    private GeocodingResponseDto convertToResponse(GeocodingApiResponse.Address address) {
        return GeocodingResponseDto.builder()
            .roadAddress(address.getRoadAddress())
            .jibunAddress(address.getJibunAddress())
            .longitude(address.getX())
            .latitude(address.getY())
            .build();
    }
}
