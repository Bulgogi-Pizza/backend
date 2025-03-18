package on.logistics.orderservice.application;

import on.logistics.orderservice.application.dtos.create.CreateOrderRequestDto;
import on.logistics.orderservice.application.dtos.create.CreateOrderResponseDto;

public interface OrderService {

  CreateOrderResponseDto createOrder(final CreateOrderRequestDto requestDto);
}
