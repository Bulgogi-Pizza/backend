package on.logistics.orderservice.application.service;

import on.logistics.orderservice.application.service.dtos.create.CreateOrderRequestDto;
import on.logistics.orderservice.application.service.dtos.create.CreateOrderResponseDto;

public interface OrderService {

  CreateOrderResponseDto createOrder(final CreateOrderRequestDto requestDto);
}
