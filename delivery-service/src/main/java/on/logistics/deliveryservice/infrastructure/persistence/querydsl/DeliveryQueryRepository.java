package on.logistics.deliveryservice.infrastructure.persistence.querydsl;

import on.logistics.deliveryservice.application.dtos.request.SearchDeliveryRequestDto;
import on.logistics.deliveryservice.domain.entity.Delivery;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryQueryRepository {

    Page<Delivery> searchDelivery(SearchDeliveryRequestDto requestDto);
}
