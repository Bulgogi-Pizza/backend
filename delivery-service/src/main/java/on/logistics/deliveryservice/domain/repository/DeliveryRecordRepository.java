package on.logistics.deliveryservice.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import on.logistics.deliveryservice.application.dtos.request.SearchDeliveryRecordRequestDto;
import on.logistics.deliveryservice.domain.entity.DeliveryRecord;
import org.springframework.data.domain.Page;

public interface DeliveryRecordRepository {

    List<DeliveryRecord> findByDeliveryId(UUID deliveryId);

    Long countByDeliveryId(UUID deliveryId);

    void save(DeliveryRecord saved);

    Optional<DeliveryRecord> findById(UUID uuid);

    void delete(DeliveryRecord deliveryRecord);

    Page<DeliveryRecord> searchDeliveryRecord(SearchDeliveryRecordRequestDto requestDto);
}
