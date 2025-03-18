package on.logistics.deliveryservice.domain.repository;

import on.logistics.deliveryservice.domain.Delivery;

public interface DeliveryRepository {

    Delivery save(Delivery delivery);
}
