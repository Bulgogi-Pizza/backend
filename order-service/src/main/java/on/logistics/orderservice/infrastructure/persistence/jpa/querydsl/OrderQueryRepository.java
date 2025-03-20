package on.logistics.orderservice.infrastructure.persistence.jpa.querydsl;

import on.logistics.orderservice.domain.entity.Order;
import on.logistics.orderservice.domain.repository.dtos.SearchOrderPageDto;
import org.springframework.data.domain.Page;

public interface OrderQueryRepository {

  Page<Order> searchOrderPage(SearchOrderPageDto searchOrderPageDto);
}
