package on.logistics.deliverymanagerservice.domain.entity.repository;

import lombok.RequiredArgsConstructor;
import on.logistics.deliverymanagerservice.domain.entity.DeliveryAssignment;
import on.logistics.deliverymanagerservice.infrastructure.jpa.DeliveryAssignmentJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeliveryAssignmentRepositoryImpl implements DeliveryAssignmentRepository {

    private final DeliveryAssignmentJpaRepository deliveryAssignmentJpaRepository;

    @Override
    public void save(DeliveryAssignment deliveryAssignment) {
        deliveryAssignmentJpaRepository.save(deliveryAssignment);
    }
}
