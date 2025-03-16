package org.onlogistics.mapservice.application.service;

import org.onlogistics.mapservice.presentation.dtos.GeocodingResponseDto;

public interface GeocodingService {

    GeocodingResponseDto getCoordinates(String query);
}
