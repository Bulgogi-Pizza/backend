package on.logistics.deliveryservice.infrastructure.repository;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.deliveryservice.domain.entity.DeliveryRecord;
import on.logistics.deliveryservice.domain.repository.DeliveryRecordRepository;
import on.logistics.deliveryservice.infrastructure.jpa.DeliveryRecordJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeliveryRecordRepositoryImpl implements DeliveryRecordRepository {

    private final DeliveryRecordJpaRepository jpaRepository;

    @Override
    public List<DeliveryRecord> findByDeliveryId(UUID deliveryId) {
        return jpaRepository.findByDeliveryId(deliveryId);
    }

    @Override
    public Long countByDeliveryId(UUID deliveryId) {
        return jpaRepository.countByDeliveryId(deliveryId);
    }

    @Override
    public void save(DeliveryRecord saved) {
        jpaRepository.save(saved);
    }
}
