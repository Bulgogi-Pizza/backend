package on.logistics.orderservice.application.service;

import on.logistics.orderservice.application.service.dtos.cancel.CancelOrderRequestDto;
import on.logistics.orderservice.application.service.dtos.cancel.CancelOrderResponseDto;
import on.logistics.orderservice.application.service.dtos.create.CreateOrderRequestDto;
import on.logistics.orderservice.application.service.dtos.create.CreateOrderResponseDto;
import on.logistics.orderservice.application.service.dtos.get.all.SearchOrderPageRequestDto;
import on.logistics.orderservice.application.service.dtos.get.all.SearchOrderPageResponseDto;
import on.logistics.orderservice.application.service.dtos.get.detail.GetOrderDetailRequestDto;
import on.logistics.orderservice.application.service.dtos.get.detail.GetOrderDetailResponseDto;
import on.logistics.orderservice.application.service.dtos.returns.accept.ReturnOrderRequestDto;
import on.logistics.orderservice.application.service.dtos.returns.accept.ReturnOrderResponseDto;
import on.logistics.orderservice.application.service.dtos.returns.denied.ReturnRequestDeniedRequestDto;
import on.logistics.orderservice.application.service.dtos.returns.denied.ReturnRequestDeniedResponseDto;
import on.logistics.orderservice.application.service.dtos.returns.request.ReturnRequestRequestDto;
import on.logistics.orderservice.application.service.dtos.returns.request.ReturnRequestResponseDto;
import on.logistics.orderservice.application.service.dtos.update.UpdateOrderRequestDto;
import on.logistics.orderservice.application.service.dtos.update.UpdateOrderResponseDto;
import on.logistics.orderservice.global.application.dtos.PageDto;
import on.logistics.orderservice.presentation.dtos.delete.DeleteOrderRequestDto;

public interface OrderService {

    CreateOrderResponseDto createOrder(final CreateOrderRequestDto requestDto);

    PageDto<SearchOrderPageResponseDto> searchOrderPage(final SearchOrderPageRequestDto requestDto);

    GetOrderDetailResponseDto getOrderDetail(final GetOrderDetailRequestDto requestDto);

    UpdateOrderResponseDto updateOrder(final UpdateOrderRequestDto requestDto);

    CancelOrderResponseDto cancelVendorOrder(final CancelOrderRequestDto requestDto);

    void deleteVendorOrder(final DeleteOrderRequestDto requestDto);

    ReturnRequestResponseDto requestReturn(final ReturnRequestRequestDto requestDto);

    ReturnRequestDeniedResponseDto denyReturnRequest(
        final ReturnRequestDeniedRequestDto requestDto);

    ReturnOrderResponseDto returnOrder(final ReturnOrderRequestDto requestDto);
}
