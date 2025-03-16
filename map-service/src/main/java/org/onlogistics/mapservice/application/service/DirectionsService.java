package org.onlogistics.mapservice.application.service;

import org.onlogistics.mapservice.presentation.dtos.DirectionsResponseDto;

public interface DirectionsService {

    DirectionsResponseDto getRoute(String start, String end, String option, int cartype);
}
