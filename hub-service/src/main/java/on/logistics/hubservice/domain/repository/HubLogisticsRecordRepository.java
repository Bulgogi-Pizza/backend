package on.logistics.hubservice.domain.repository;

import java.util.List;
import on.logistics.hubservice.domain.entity.HubLogisticsRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface HubLogisticsRecordRepository {

    void saveAll(List<HubLogisticsRecord> records);
}
