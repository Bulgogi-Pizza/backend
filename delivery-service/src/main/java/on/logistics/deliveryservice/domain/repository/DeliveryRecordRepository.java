package on.logistics.deliveryservice.domain.repository;

import java.util.List;
import java.util.UUID;
import on.logistics.deliveryservice.domain.entity.DeliveryRecord;

public interface DeliveryRecordRepository {

    List<DeliveryRecord> findByDeliveryId(UUID deliveryId);

    Long countByDeliveryId(UUID deliveryId);

    void save(DeliveryRecord saved);
}
