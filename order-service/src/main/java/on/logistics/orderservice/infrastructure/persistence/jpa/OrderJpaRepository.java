package on.logistics.orderservice.infrastructure.persistence.jpa;

import java.util.UUID;
import on.logistics.orderservice.domain.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, UUID> {

  Page<Order> findAllByOrdererUserId(UUID ordererUserId, Pageable pageable);
}
