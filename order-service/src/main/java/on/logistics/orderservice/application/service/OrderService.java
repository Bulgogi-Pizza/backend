package on.logistics.orderservice.application.service;

import on.logistics.orderservice.application.service.dtos.create.CreateOrderRequestDto;
import on.logistics.orderservice.application.service.dtos.create.CreateOrderResponseDto;
import on.logistics.orderservice.application.service.dtos.get.all.SearchOrderPageResponseDto;
import on.logistics.orderservice.application.service.dtos.get.all.SearchOrderPageRequestDto;
import on.logistics.orderservice.global.application.dtos.PageDto;

public interface OrderService {

  CreateOrderResponseDto createOrder(final CreateOrderRequestDto requestDto);

  PageDto<SearchOrderPageResponseDto> searchOrderPage(
      final SearchOrderPageRequestDto requestDto);
}
