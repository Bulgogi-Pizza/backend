package on.logistics.deliverymanagerservice.infrastructure.jpa;

import java.util.Optional;
import java.util.UUID;
import on.logistics.deliverymanagerservice.domain.entity.DeliveryManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryManagerJpaRepository extends JpaRepository<DeliveryManager, UUID> {

    Optional<DeliveryManager> findByIdAndIsDeleted(UUID id, boolean isDeleted);

}
