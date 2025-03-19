package on.logistics.deliveryservice.application.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.deliveryservice.application.dtos.request.CreateDeliveryRecordRequestDto;
import on.logistics.deliveryservice.application.dtos.request.SearchDeliveryRecordRequestDto;
import on.logistics.deliveryservice.application.dtos.request.UpdateDeliveryRecordRequestDto;
import on.logistics.deliveryservice.application.dtos.request.UpdateDeliveryRecordStatusRequestDto;
import on.logistics.deliveryservice.domain.dtos.CreateDeliveryRecordDto;
import on.logistics.deliveryservice.domain.entity.Delivery;
import on.logistics.deliveryservice.domain.entity.DeliveryRecord;
import on.logistics.deliveryservice.domain.enums.DeliveryRecordStatus;
import on.logistics.deliveryservice.domain.enums.DeliveryStatus;
import on.logistics.deliveryservice.domain.repository.DeliveryRecordRepository;
import on.logistics.deliveryservice.exception.DeliveryRecordException;
import on.logistics.deliveryservice.exception.DeliveryRecordExceptionCode;
import on.logistics.deliveryservice.global.application.dtos.PageDto;
import on.logistics.deliveryservice.infrastructure.clients.hub.HubServiceClient;
import on.logistics.deliveryservice.infrastructure.clients.map.MapServiceClient;
import on.logistics.deliveryservice.infrastructure.clients.map.feign.dtos.GetEstimateInfo;
import on.logistics.deliveryservice.presentation.dtos.response.CreateDeliveryRecordResponse;
import on.logistics.deliveryservice.presentation.dtos.response.GetDeliveryRecordResponse;
import on.logistics.deliveryservice.presentation.dtos.response.SearchDeliveryRecordResponse;
import on.logistics.deliveryservice.presentation.dtos.response.UpdateDeliveryRecordResponse;
import on.logistics.deliveryservice.presentation.dtos.response.UpdateDeliveryRecordStatusResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private final DeliveryRecordRepository deliveryRecordRepository;
    private final DeliveryService deliveryService;
    private final MapServiceClient mapServiceClient;
    private final HubServiceClient hubServiceClient;

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

    @Override
    @Transactional
    public UpdateDeliveryRecordResponse updateActualDeliveryRecord(
        UpdateDeliveryRecordRequestDto requestDto) {
        DeliveryRecord deliveryRecord = getOrElseThrow(requestDto.deliveryRecordId());
        deliveryRecord.update(requestDto.actualDistance(), requestDto.actualDuration());

        return UpdateDeliveryRecordResponse.of(deliveryRecord.getId());
    }

    @Override
    @Transactional
    public void deleteDeliveryRecord(UUID id) {
        DeliveryRecord deliveryRecord = getOrElseThrow(id);
        deliveryRecordRepository.delete(deliveryRecord);
    }

    @Override
    @Transactional
    public UpdateDeliveryRecordStatusResponse updateStatusDeliveryRecord(
        UpdateDeliveryRecordStatusRequestDto requestDto) {
        DeliveryRecord deliveryRecord = getOrElseThrow(requestDto.deliveryRecordId());
        if (deliveryRecord.getDelivery().getStatus() == DeliveryStatus.CANCEL) {
            throw new DeliveryRecordException(
                DeliveryRecordExceptionCode.DELIVERY_RECORD_DELIVERY_STATUS_CANCEL);
        }
        deliveryRecord.updateStatus(requestDto.status());
        return UpdateDeliveryRecordStatusResponse.of(deliveryRecord.getId());
    }

    @Override
    public GetDeliveryRecordResponse getDeliveryRecord(UUID id) {
        DeliveryRecord deliveryRecord = getOrElseThrow(id);
        return GetDeliveryRecordResponse.from(deliveryRecord);
    }

    @Override
    public PageDto<SearchDeliveryRecordResponse> searchDeliveryRecord(
        SearchDeliveryRecordRequestDto requestDto) {
        Page<DeliveryRecord> deliveryRecordPage = deliveryRecordRepository.searchDeliveryRecord(
            requestDto);
        Page<SearchDeliveryRecordResponse> responsePage = deliveryRecordPage.map(
            SearchDeliveryRecordResponse::from);
        return PageDto.from(responsePage);
    }

    private DeliveryRecord getOrElseThrow(UUID deliveryRecordId) {
        return deliveryRecordRepository.findById(deliveryRecordId).orElseThrow(
            () -> new DeliveryRecordException(
                DeliveryRecordExceptionCode.DELIVERY_RECORD_NOT_FOUND));
    }

    private CreateDeliveryRecordDto getCreateDeliveryRecordDto(
        CreateDeliveryRecordRequestDto requestDto) {

        Long deliveryRecordCount = deliveryRecordRepository.countByDeliveryId(
            requestDto.deliveryId());

        long sequence = 1L;
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
        /*
        GetHubInfo startHubInfo = hubServiceClient.getHubInfo(startHubId);
        GetHubInfo endHubInfo = hubServiceClient.getHubInfo(endHubId);
        String start = "" + startHubInfo.longitude() + "," + startHubInfo.latitude();
        String end = "" + endHubInfo.longitude() + "," + endHubInfo.latitude();
        */

        String start = "126.8737955,37.6403771";
        String end = "127.12345,37.12345";

        return mapServiceClient.getEstimate(start, end);
    }
}
