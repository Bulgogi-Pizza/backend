package on.logistics.deliveryservice.domain.dtos;

import java.util.UUID;
import on.logistics.deliveryservice.application.dtos.request.CreateDeliveryRecordRequestDto;
import on.logistics.deliveryservice.domain.enums.DeliveryRecordStatus;
import on.logistics.deliveryservice.infrastructure.clients.map.feign.dtos.GetEstimateInfo;

public record CreateDeliveryRecordDto(UUID deliveryId, Long sequence, DeliveryRecordStatus status,
                                      UUID startHubId, UUID endHubId, Long estimatedDistance,
                                      Long estimatedDuration, UUID userId) {

    public static CreateDeliveryRecordDto from(CreateDeliveryRecordRequestDto requestDto,
        Long sequence, DeliveryRecordStatus status, GetEstimateInfo getEstimateInfo) {
        return new CreateDeliveryRecordDto(requestDto.deliveryId(), sequence, status,
            requestDto.deliveryRecordStartHubId(), requestDto.deliveryRecordEndHubId(),
            getEstimateInfo.summary().distance(),
            getEstimateInfo.summary().duration(), requestDto.userId());
    }

    public static CreateDeliveryRecordDto from(CreateDeliveryRecordRequestDto requestDto,
        Long sequence, DeliveryRecordStatus status) {
        return new CreateDeliveryRecordDto(requestDto.deliveryId(), sequence, status,
            requestDto.deliveryRecordStartHubId(), requestDto.deliveryRecordEndHubId(),
            0L,
            0L, requestDto.userId());
    }
}
