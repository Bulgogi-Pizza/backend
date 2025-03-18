package on.logistics.orderservice.infrastructure.jpa;

import java.util.UUID;
import on.logistics.orderservice.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, UUID> {

}
