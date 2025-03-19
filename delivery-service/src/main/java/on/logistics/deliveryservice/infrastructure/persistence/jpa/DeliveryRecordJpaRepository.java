package on.logistics.deliveryservice.infrastructure.persistence.jpa;

import java.util.List;
import java.util.UUID;
import on.logistics.deliveryservice.domain.entity.DeliveryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRecordJpaRepository extends JpaRepository<DeliveryRecord, UUID> {

    List<DeliveryRecord> findByDeliveryId(UUID deliveryId);

    Long countByDeliveryId(UUID deliveryId);
}
