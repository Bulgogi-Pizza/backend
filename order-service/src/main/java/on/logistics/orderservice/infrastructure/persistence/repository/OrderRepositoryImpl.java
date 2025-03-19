package on.logistics.orderservice.infrastructure.persistence.repository;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.orderservice.domain.entity.Order;
import on.logistics.orderservice.domain.repository.OrderRepository;
import on.logistics.orderservice.infrastructure.persistence.jpa.OrderJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

  private final OrderJpaRepository orderJpaRepository;

  @Override
  public Order save(Order order) {
    return orderJpaRepository.save(order);
  }

  @Override
  public Page<Order> findAllByOrdererUserId(UUID ordererUserId, Pageable pageable) {
    return orderJpaRepository.findAllByOrdererUserId(ordererUserId, pageable);
  }
}
