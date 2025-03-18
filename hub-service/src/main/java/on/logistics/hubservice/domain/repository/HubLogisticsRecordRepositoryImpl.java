package on.logistics.hubservice.domain.repository;

import java.util.List;
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
}
