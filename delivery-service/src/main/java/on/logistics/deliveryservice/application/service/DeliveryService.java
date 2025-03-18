package on.logistics.deliveryservice.application.service;

import on.logistics.deliveryservice.application.dtos.request.CreateDeliveryRequestDto;
import on.logistics.deliveryservice.presentation.dtos.response.CreateDeliveryResponse;

public interface DeliveryService {

    CreateDeliveryResponse createDelivery(CreateDeliveryRequestDto requestDto);
}
