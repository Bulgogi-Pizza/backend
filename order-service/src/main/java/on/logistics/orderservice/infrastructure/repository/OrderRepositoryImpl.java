package on.logistics.orderservice.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import on.logistics.orderservice.domain.entity.Order;
import on.logistics.orderservice.domain.repository.OrderRepository;
import on.logistics.orderservice.infrastructure.jpa.OrderJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

  private final OrderJpaRepository orderJpaRepository;

  @Override
  public Order save(Order order) {
    return orderJpaRepository.save(order);
  }
}
