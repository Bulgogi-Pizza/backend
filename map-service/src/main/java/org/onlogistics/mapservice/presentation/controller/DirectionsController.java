package org.onlogistics.mapservice.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.onlogistics.mapservice.application.service.DirectionsService;
import org.onlogistics.mapservice.global.presentation.dtos.CommonResponse;
import org.onlogistics.mapservice.presentation.dtos.DirectionsResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DirectionsController {

    private final DirectionsService directionsService;

    @GetMapping("/api/v1/maps/route")
    public ResponseEntity<CommonResponse<DirectionsResponseDto>> getRoute(
        @RequestParam("start") String start,
        @RequestParam("end") String end,
        @RequestParam(value = "option", required = false, defaultValue = "traoptimal") String option,
        @RequestParam(value = "cartype", required = false, defaultValue = "2") int cartype) {
        final var responseDto = directionsService.getRoute(start, end, option, cartype);
        return ResponseEntity.ok().body(CommonResponse.success(responseDto));
    }

}
