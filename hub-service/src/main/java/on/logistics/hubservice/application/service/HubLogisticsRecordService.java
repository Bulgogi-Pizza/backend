package on.logistics.hubservice.application.service;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.hubservice.application.dtos.request.RetrievalLogisticsRequestDto;
import on.logistics.hubservice.application.dtos.request.StorageLogisticsRequestDto;
import on.logistics.hubservice.domain.entity.HubLogisticsRecord;
import on.logistics.hubservice.domain.repository.HubLogisticsRecordRepository;
import on.logistics.hubservice.domain.repository.HubRepository;
import on.logistics.hubservice.exception.HubException;
import on.logistics.hubservice.exception.HubExceptionCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HubLogisticsRecordService {

    private final HubLogisticsRecordRepository hubLogisticsRecordRepository;
    private final HubRepository hubRepository;

    @Transactional
    public void storage(StorageLogisticsRequestDto requestDto) {
        validateHubExists(requestDto.hubId());
        // TODO: deliveryId가 실제로 존재하는지 확인 필요
        List<HubLogisticsRecord> records = requestDto.storageLogisticsIds()
            .stream()
            .map(id -> HubLogisticsRecord.storage(requestDto.hubId(), id))
            .toList();
        hubLogisticsRecordRepository.saveAll(records);
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
}
