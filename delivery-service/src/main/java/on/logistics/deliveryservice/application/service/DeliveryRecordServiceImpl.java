package on.logistics.deliveryservice.application.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.deliveryservice.application.dtos.request.CreateDeliveryRecordRequestDto;
import on.logistics.deliveryservice.domain.dtos.CreateDeliveryRecordDto;
import on.logistics.deliveryservice.domain.entity.Delivery;
import on.logistics.deliveryservice.domain.entity.DeliveryRecord;
import on.logistics.deliveryservice.domain.enums.DeliveryRecordStatus;
import on.logistics.deliveryservice.domain.repository.DeliveryRecordRepository;
import on.logistics.deliveryservice.infrastructure.client.map.MapServiceClient;
import on.logistics.deliveryservice.infrastructure.client.map.feign.dtos.GetEstimateInfo;
import on.logistics.deliveryservice.presentation.dtos.response.CreateDeliveryRecordResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private final DeliveryRecordRepository deliveryRecordRepository;
    private final DeliveryService deliveryService;
    private final MapServiceClient mapServiceClient;

    @Override
    @Transactional
    public CreateDeliveryRecordResponse createDeliveryRecord(
        CreateDeliveryRecordRequestDto requestDto) {

        Delivery delivery = deliveryService.getOrElseThrow(requestDto.deliveryId());
        CreateDeliveryRecordDto createEntityDto = getCreateDeliveryRecordDto(requestDto);
        DeliveryRecord saved = DeliveryRecord.create(createEntityDto, delivery);
        deliveryRecordRepository.save(saved);
        return CreateDeliveryRecordResponse.of(saved.getId());
    }

    private CreateDeliveryRecordDto getCreateDeliveryRecordDto(
        CreateDeliveryRecordRequestDto requestDto) {

        Long deliveryRecordCount = deliveryRecordRepository.countByDeliveryId(
            requestDto.deliveryId());

        long sequence = 0L;
        DeliveryRecordStatus deliveryRecordStatus = DeliveryRecordStatus.HUB_MOVING;

        if (deliveryRecordCount != 0) {
            sequence = deliveryRecordCount + 1;
            deliveryRecordStatus = DeliveryRecordStatus.HUB_WAITING;
        }

        GetEstimateInfo getEstimateInfo = getEstimateInfo(requestDto.deliveryRecordStartHubId(),
            requestDto.deliveryRecordEndHubId());
        return CreateDeliveryRecordDto.from(requestDto, sequence, deliveryRecordStatus,
            getEstimateInfo);
    }

    public GetEstimateInfo getEstimateInfo(UUID startHubId, UUID endHubId) {
        // todo: 허브에 조회 요청. 조회 요청으로 받은 허브 위도, 경도로 예상 시간, 예상 거리 받아옴
        String start = "126.8737955,37.6403771";
        String end = "127.12345,37.12345";

        return mapServiceClient.getEstimate(start, end);
    }
}
