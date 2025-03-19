package on.logistics.deliveryservice.domain.dtos;

import java.util.UUID;
import on.logistics.deliveryservice.application.dtos.request.CreateDeliveryRecordRequestDto;
import on.logistics.deliveryservice.domain.enums.DeliveryRecordStatus;
import on.logistics.deliveryservice.infrastructure.client.map.feign.dtos.GetEstimateInfo;

public record CreateDeliveryRecordDto(UUID deliveryId, Long sequence, DeliveryRecordStatus status,
                                      UUID startHubId, UUID endHubId, double estimatedDistance,
                                      Long estimatedDuration, UUID deliveryManagerId) {

    public static CreateDeliveryRecordDto from(CreateDeliveryRecordRequestDto requestDto,
        Long sequence, DeliveryRecordStatus status, GetEstimateInfo getEstimateInfo) {
        return new CreateDeliveryRecordDto(requestDto.deliveryId(), sequence, status,
            requestDto.deliveryRecordStartHubId(), requestDto.deliveryRecordEndHubId(),
            getEstimateInfo.summary().distance() / 1000.0,
            getEstimateInfo.summary().duration() / 60000L, requestDto.deliveryManagerId());
    }
}
