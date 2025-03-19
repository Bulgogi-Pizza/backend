package on.logistics.orderservice.domain.repository;

import java.util.UUID;
import on.logistics.orderservice.domain.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepository {

  Order save(Order order);

  Page<Order> findAllByOrdererUserId(UUID ordererUserId, Pageable pageable);
}
