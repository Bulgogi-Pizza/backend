package on.logistics.deliverymanagerservice.domain.entity.repository;

import java.util.Optional;
import java.util.UUID;
import on.logistics.deliverymanagerservice.domain.entity.DeliveryManager;
import on.logistics.deliverymanagerservice.domain.entity.DeliveryType;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryManagerRepository {

    DeliveryManager save(DeliveryManager deliveryManager);

    Integer findMaxSequenceByHubIdAndType(UUID hubId, DeliveryType deliveryType);

    Optional<DeliveryManager> findByIdAndIsDeleted(UUID id, boolean isDeleted);

    Optional<DeliveryManager> findLastAssignedManager(UUID hubId, DeliveryType type);

    Optional<DeliveryManager> findNextDeliveryManager(UUID hubId, Integer lastSequence);

    Optional<DeliveryManager> findFirstByHubIdOrderBySequenceAsc(UUID hubId, DeliveryType type);
}
