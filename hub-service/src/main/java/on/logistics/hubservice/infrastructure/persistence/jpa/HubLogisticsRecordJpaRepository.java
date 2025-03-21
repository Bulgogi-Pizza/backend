package on.logistics.hubservice.infrastructure.persistence.jpa;

import java.util.List;
import java.util.UUID;
import on.logistics.hubservice.domain.entity.HubLogisticsRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HubLogisticsRecordJpaRepository extends JpaRepository<HubLogisticsRecord, UUID> {

    List<HubLogisticsRecord> findAllByDeliveryIdIn(List<UUID> retrievalLogisticsIds);
}
