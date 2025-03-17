package on.logistics.mapservice.presentation.dtos;

import lombok.Builder;

@Builder
public record GeocodingResponseDto(
    String roadAddress,
    String jibunAddress,
    String longitude,
    String latitude
) {

}