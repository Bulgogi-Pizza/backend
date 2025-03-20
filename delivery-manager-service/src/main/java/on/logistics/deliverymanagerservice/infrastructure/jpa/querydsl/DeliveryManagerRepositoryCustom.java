package on.logistics.deliverymanagerservice.infrastructure.jpa.querydsl;

import java.util.Optional;
import java.util.UUID;
import on.logistics.deliverymanagerservice.domain.entity.DeliveryManager;
import on.logistics.deliverymanagerservice.domain.entity.DeliveryType;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryManagerRepositoryCustom {
    Optional<DeliveryManager> findLastAssignedManager(UUID hubId, DeliveryType type);

    Optional<DeliveryManager> findNextDeliveryManager(UUID hubId, Integer sequence);

    Optional<DeliveryManager> findFirstByHubIdOrderBySequenceAsc(UUID hubId, DeliveryType type);
}
