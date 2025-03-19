package on.logistics.orderservice.application.service;

import on.logistics.orderservice.application.service.dtos.create.CreateOrderRequestDto;
import on.logistics.orderservice.application.service.dtos.create.CreateOrderResponseDto;
import on.logistics.orderservice.application.service.dtos.get.all.GetOrderPageByOrdererUserIdResponseDto;
import on.logistics.orderservice.application.service.dtos.get.all.GetOrdererPageByOrdererUserIdRequestDto;
import on.logistics.orderservice.global.application.dtos.PageDto;

public interface OrderService {

  CreateOrderResponseDto createOrder(final CreateOrderRequestDto requestDto);

  PageDto<GetOrderPageByOrdererUserIdResponseDto> getOrderPageByOrdererUserId(
      final GetOrdererPageByOrdererUserIdRequestDto requestDto);
}
