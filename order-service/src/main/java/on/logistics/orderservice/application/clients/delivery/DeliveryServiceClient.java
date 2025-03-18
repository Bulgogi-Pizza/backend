package on.logistics.orderservice.application.clients.delivery;

import on.logistics.orderservice.application.clients.delivery.dtos.DeliveryRequestDto;

public interface DeliveryServiceClient {

  void deliveryRequest(DeliveryRequestDto requestDto);
}
