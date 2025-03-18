package on.logistics.deliveryservice.domain.repository;

import java.util.Optional;
import java.util.UUID;
import on.logistics.deliveryservice.application.dtos.request.SearchDeliveryRequestDto;
import on.logistics.deliveryservice.domain.Delivery;
import org.springframework.data.domain.Page;

public interface DeliveryRepository {

    Delivery save(Delivery delivery);

    Optional<Delivery> findById(UUID uuid);

    void delete(Delivery delivery);

    Page<Delivery> searchDelivery(SearchDeliveryRequestDto requestDto);
}
