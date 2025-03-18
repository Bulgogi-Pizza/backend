package on.logistics.hubservice.domain.repository;

import java.util.List;
import java.util.UUID;
import on.logistics.hubservice.domain.entity.HubLogisticsRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface HubLogisticsRecordRepository {

    void saveAll(List<HubLogisticsRecord> records);

    List<HubLogisticsRecord> findAllByDeliveryIdIn(List<UUID> retrievalLogisticsIds);
}
