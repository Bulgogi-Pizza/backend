package on.logistics.hubservice.infrastructure.jpa;

import java.util.UUID;
import on.logistics.hubservice.domain.entity.HubLogisticsRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HubLogisticsRecordJpaRepository extends JpaRepository<HubLogisticsRecord, UUID> {

}
