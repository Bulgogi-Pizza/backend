package on.logistics.hubservice.application.service;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.hubservice.application.dtos.request.AssignDeliveryManagerRequestDto;
import on.logistics.hubservice.application.dtos.request.RetrievalLogisticsRequestDto;
import on.logistics.hubservice.application.dtos.request.StorageLogisticsRequestDto;
import on.logistics.hubservice.domain.entity.HubLogisticsRecord;
import on.logistics.hubservice.domain.repository.HubLogisticsRecordRepository;
import on.logistics.hubservice.domain.repository.HubRepository;
import on.logistics.hubservice.exception.HubException;
import on.logistics.hubservice.exception.HubExceptionCode;
import on.logistics.hubservice.infrastructure.clients.deliverymanager.DeliveryManagerServiceClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class HubLogisticsRecordService {

    private final HubLogisticsRecordRepository hubLogisticsRecordRepository;
    private final HubRepository hubRepository;
    private final DeliveryManagerServiceClient deliveryManagerServiceClient;

    @Transactional
    public void storage(StorageLogisticsRequestDto requestDto) {
        validateHubExists(requestDto.hubId());
        // TODO: deliveryId가 실제로 존재하는지 확인 필요
        List<HubLogisticsRecord> records = requestDto.storageLogisticsIds()
            .stream()
            .map(id -> HubLogisticsRecord.storage(requestDto.hubId(), id))
            .toList();
        hubLogisticsRecordRepository.saveAll(records);

        assignDeliveryManager(requestDto);
    }

    public void retrieval(RetrievalLogisticsRequestDto requestDto) {
        validateHubExists(requestDto.hubId());
        List<HubLogisticsRecord> records = hubLogisticsRecordRepository.findAllByDeliveryIdIn(
            requestDto.retrievalLogisticsIds());
        records.forEach(HubLogisticsRecord::retrieval);
        hubLogisticsRecordRepository.saveAll(records);
    }

    private void validateHubExists(UUID hubId) {
        if (!hubRepository.existsById(hubId)) {
            throw new HubException(HubExceptionCode.HUB_NOT_FOUND);
        }
    }

    private void assignDeliveryManager(
        StorageLogisticsRequestDto logistics) {
        logistics.storageLogisticsIds().stream()
            .forEach(deliverId -> {
                // TODO: 배송 간 이동정보로 다음 목적지와 배송타입을 받아와야함
                final var requestDto = AssignDeliveryManagerRequestDto.of(
                    deliverId,
                    logistics.hubId(),
                    "COMPANY_DELIVERY"); // TODO: 다음 목적지에 대한 값 받을 때 응답으로 온 데이터 넣기
                final var assignedDeliveryManager = deliveryManagerServiceClient.assignDeliveryManager(
                    requestDto);
                log.info(
                    "assingedDeliveryManager ID: " + assignedDeliveryManager.deliveryManagerId());
                // TODO: 배송 간 이동정보로 deliveryMangaerId (배정된 배송담당자)를 보내줘야함
            });
    }
}
