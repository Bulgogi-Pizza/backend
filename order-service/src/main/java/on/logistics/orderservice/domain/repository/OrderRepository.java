package on.logistics.orderservice.domain.repository;

import on.logistics.orderservice.domain.entity.Order;

public interface OrderRepository {

  Order save(Order order);
}
