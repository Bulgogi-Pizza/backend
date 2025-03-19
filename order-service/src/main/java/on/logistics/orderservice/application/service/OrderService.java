package on.logistics.orderservice.application.service;

import on.logistics.orderservice.application.service.dtos.cancel.CancelOrderRequestDto;
import on.logistics.orderservice.application.service.dtos.cancel.CancelOrderResponseDto;
import on.logistics.orderservice.application.service.dtos.create.CreateOrderRequestDto;
import on.logistics.orderservice.application.service.dtos.create.CreateOrderResponseDto;
import on.logistics.orderservice.application.service.dtos.get.all.SearchOrderPageRequestDto;
import on.logistics.orderservice.application.service.dtos.get.all.SearchOrderPageResponseDto;
import on.logistics.orderservice.application.service.dtos.get.detail.GetOrderDetailRequestDto;
import on.logistics.orderservice.application.service.dtos.get.detail.GetOrderDetailResponseDto;
import on.logistics.orderservice.application.service.dtos.update.UpdateOrderRequestDto;
import on.logistics.orderservice.application.service.dtos.update.UpdateOrderResponseDto;
import on.logistics.orderservice.global.application.dtos.PageDto;

public interface OrderService {

  CreateOrderResponseDto createOrder(final CreateOrderRequestDto requestDto);

  PageDto<SearchOrderPageResponseDto> searchOrderPage(final SearchOrderPageRequestDto requestDto);

  GetOrderDetailResponseDto getOrderDetail(final GetOrderDetailRequestDto requestDto);

  UpdateOrderResponseDto updateOrder(final UpdateOrderRequestDto requestDto);

  CancelOrderResponseDto cancelOrder(final CancelOrderRequestDto requestDto);
}
