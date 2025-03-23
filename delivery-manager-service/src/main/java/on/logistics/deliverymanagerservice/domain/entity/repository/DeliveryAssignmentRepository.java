package on.logistics.deliverymanagerservice.domain.entity.repository;

import on.logistics.deliverymanagerservice.domain.entity.DeliveryAssignment;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryAssignmentRepository {

    void save(DeliveryAssignment deliveryAssignment);
}
