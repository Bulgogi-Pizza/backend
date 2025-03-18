package on.logistics.deliveryservice.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.deliveryservice.application.dtos.request.SearchDeliveryRequestDto;
import on.logistics.deliveryservice.domain.Delivery;
import on.logistics.deliveryservice.domain.repository.DeliveryRepository;
import on.logistics.deliveryservice.infrastructure.jpa.DeliveryJpaRepository;
import on.logistics.deliveryservice.infrastructure.querydsl.DeliveryQueryRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeliveryRepositoryImpl implements DeliveryRepository {

    private final DeliveryJpaRepository deliveryJpaRepository;
    private final DeliveryQueryRepository deliveryQueryRepository;

    @Override
    public Delivery save(Delivery delivery) {
        return deliveryJpaRepository.save(delivery);
    }

    @Override
    public Optional<Delivery> findById(UUID uuid) {
        return deliveryJpaRepository.findById(uuid);
    }

    @Override
    public void delete(Delivery delivery) {
        deliveryJpaRepository.delete(delivery);
    }

    @Override
    public Page<Delivery> searchDelivery(SearchDeliveryRequestDto requestDto) {
        return null;
    }
}
