package on.logistics.orderservice.application.service;

import on.logistics.orderservice.infrastructure.clients.delivery.dtos.DeliveryRequestDto;

public interface DeliveryService {

    void deliveryRequest(DeliveryRequestDto requestDto);
}
