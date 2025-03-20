package on.logistics.hubservice.domain.repository;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.hubservice.domain.entity.HubLogisticsRecord;
import on.logistics.hubservice.infrastructure.jpa.HubLogisticsRecordJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HubLogisticsRecordRepositoryImpl implements HubLogisticsRecordRepository {

    private final HubLogisticsRecordJpaRepository hubLogisticsRecordJpaRepository;

    @Override
    public void saveAll(List<HubLogisticsRecord> records) {
        hubLogisticsRecordJpaRepository.saveAll(records);
    }

    @Override
    public List<HubLogisticsRecord> findAllByDeliveryIdIn(List<UUID> retrievalLogisticsIds) {
        return hubLogisticsRecordJpaRepository.findAllByDeliveryIdIn(retrievalLogisticsIds);
    }
}
