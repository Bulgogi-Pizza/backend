package on.logistics.deliverymanagerservice.infrastructure.jpa;

import java.util.UUID;
import on.logistics.deliverymanagerservice.domain.entity.DeliveryAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryAssignmentJpaRepository extends JpaRepository<DeliveryAssignment, UUID> {

}
