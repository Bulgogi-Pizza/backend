package on.logistics.deliveryservice.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.deliveryservice.application.dtos.request.SearchDeliveryRecordRequestDto;
import on.logistics.deliveryservice.domain.entity.DeliveryRecord;
import on.logistics.deliveryservice.domain.repository.DeliveryRecordRepository;
import on.logistics.deliveryservice.infrastructure.jpa.DeliveryRecordJpaRepository;
import on.logistics.deliveryservice.infrastructure.querydsl.DeliveryRecordQueryRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeliveryRecordRepositoryImpl implements DeliveryRecordRepository {

    private final DeliveryRecordJpaRepository jpaRepository;
    private final DeliveryRecordQueryRepository queryRepository;

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

    @Override
    public Optional<DeliveryRecord> findById(UUID id) {
        return jpaRepository.findById(id);
    }

    @Override
    public void delete(DeliveryRecord deliveryRecord) {
        jpaRepository.delete(deliveryRecord);
    }

    @Override
    public Page<DeliveryRecord> searchDeliveryRecord(SearchDeliveryRecordRequestDto requestDto) {

        return queryRepository.searchDeliveryRecord(requestDto);
    }
}
