package on.logistics.deliveryservice.infrastructure.jpa;

import java.util.UUID;
import on.logistics.deliveryservice.domain.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryJpaRepository extends JpaRepository<Delivery, UUID> {

}
