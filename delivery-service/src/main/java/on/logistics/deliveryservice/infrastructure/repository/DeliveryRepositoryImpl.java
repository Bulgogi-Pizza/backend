package on.logistics.deliveryservice.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import on.logistics.deliveryservice.domain.Delivery;
import on.logistics.deliveryservice.domain.repository.DeliveryRepository;
import on.logistics.deliveryservice.infrastructure.jpa.DeliveryJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeliveryRepositoryImpl implements DeliveryRepository {

    private final DeliveryJpaRepository deliveryJpaRepository;

    @Override
    public Delivery save(Delivery delivery) {
        return deliveryJpaRepository.save(delivery);
    }
}
