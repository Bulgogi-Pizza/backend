package on.logistics.orderservice.presentation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.orderservice.application.service.OrderService;
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
import on.logistics.orderservice.global.domain.Passport;
import on.logistics.orderservice.global.presentation.dtos.CommonResponse;
import on.logistics.orderservice.global.utils.PassportUtil;
import on.logistics.orderservice.presentation.dtos.create.CreateOrderRequest;
import on.logistics.orderservice.presentation.dtos.delete.DeleteOrderRequestDto;
import on.logistics.orderservice.presentation.dtos.update.UpdateOrderRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final PassportUtil passportUtil;

    @PostMapping
    public ResponseEntity<CommonResponse<CreateOrderResponseDto>> createOrder(
        @RequestBody @Valid final CreateOrderRequest request,
        HttpServletRequest servletRequest
    ) {
        final Passport passport = passportUtil.getPassportBy(servletRequest);
        final var requestDto = CreateOrderRequestDto.of(
            request, passport.getUserId(), passport.getNickname());
        final var responseDto = orderService.createOrder(requestDto);
        return ResponseEntity.ok(CommonResponse.success(responseDto));
    }

    @GetMapping
    public ResponseEntity<CommonResponse<PageDto<SearchOrderPageResponseDto>>> searchOrderPage(
        @RequestParam(required = false) final UUID ordererUserId,
        @RequestParam(required = false) final String userNickname,
        @RequestParam(required = false) final UUID ordererCompanyId,
        @RequestParam(required = false) final String ordererCompanyName,
        @RequestParam(required = false) final UUID vendorCompanyId,
        @RequestParam(required = false) final String vendorCompanyName,
        final Pageable pageable,
        HttpServletRequest servletRequest
    ) {
        final Passport passport = passportUtil.getPassportBy(servletRequest);
        final var requestDto = SearchOrderPageRequestDto.of(
            pageable, passport.getUserId(), passport.getRole(), ordererUserId, userNickname,
            ordererCompanyId, ordererCompanyName, vendorCompanyId, vendorCompanyName
        );
        final var responseDto = orderService.searchOrderPage(requestDto);
        return ResponseEntity.ok(CommonResponse.success(responseDto));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<CommonResponse<GetOrderDetailResponseDto>> getOrderDetail(
        @PathVariable final UUID orderId,
        HttpServletRequest servletRequest
    ) {
        final Passport passport = passportUtil.getPassportBy(servletRequest);
        final var requestDto = GetOrderDetailRequestDto.of(
            orderId, passport.getUserId(), passport.getRole());
        final var responseDto = orderService.getOrderDetail(requestDto);
        return ResponseEntity.ok(CommonResponse.success(responseDto));
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<CommonResponse<UpdateOrderResponseDto>> updateOrder(
        @PathVariable final UUID orderId,
        @RequestBody @Valid final UpdateOrderRequest request,
        HttpServletRequest servletRequest
    ) {
        final Passport passport = passportUtil.getPassportBy(servletRequest);
        final var requestDto = UpdateOrderRequestDto.of(
            request, orderId, passport.getUserId(), passport.getRole());
        final var responseDto = orderService.updateOrder(requestDto);
        return ResponseEntity.ok(CommonResponse.success(responseDto));
    }

    @PatchMapping("/{orderId}/cancel/{vendorOrderId}")
    public ResponseEntity<CommonResponse<CancelOrderResponseDto>> cancelVendorOrder(
        @PathVariable final UUID orderId,
        @PathVariable final UUID vendorOrderId,
        HttpServletRequest servletRequest
    ) {
        final Passport passport = passportUtil.getPassportBy(servletRequest);
        final var requestDto = CancelOrderRequestDto.of(
            orderId, vendorOrderId, passport.getUserId(), passport.getRole());
        final var responseDto = orderService.cancelVendorOrder(requestDto);
        return ResponseEntity.ok(CommonResponse.success(responseDto));
    }

    @DeleteMapping("/{orderId}/{vendorOrderId}")
    public ResponseEntity<CommonResponse<Void>> deleteVendorOrder(
        @PathVariable final UUID orderId,
        @PathVariable final UUID vendorOrderId,
        HttpServletRequest servletRequest
    ) {
        final Passport passport = passportUtil.getPassportBy(servletRequest);
        final DeleteOrderRequestDto requestDto = DeleteOrderRequestDto.of(
            orderId, vendorOrderId, passport.getUserId(), passport.getRole());
        orderService.deleteVendorOrder(requestDto);
        return ResponseEntity.ok(CommonResponse.success());
    }

    @PatchMapping("/{orderId}/return/request/{vendorOrderId}")
    public ResponseEntity<CommonResponse<ReturnRequestResponseDto>> requestReturn(
        @PathVariable final UUID orderId,
        @PathVariable final UUID vendorOrderId,
        HttpServletRequest servletRequest
    ) {
        final Passport passport = passportUtil.getPassportBy(servletRequest);
        final var requestDto = ReturnRequestRequestDto.of(
            orderId, vendorOrderId, passport.getUserId(), passport.getRole());
        final var responseDto = orderService.requestReturn(requestDto);
        return ResponseEntity.ok(CommonResponse.success(responseDto));
    }

    @PatchMapping("/{orderId}/return/denied/{vendorOrderId}")
    public ResponseEntity<CommonResponse<ReturnRequestDeniedResponseDto>> denyReturnRequest(
        @PathVariable final UUID orderId,
        @PathVariable final UUID vendorOrderId,
        HttpServletRequest servletRequest
    ) {
        final Passport passport = passportUtil.getPassportBy(servletRequest);
        final var requestDto = ReturnRequestDeniedRequestDto.of(
            orderId, vendorOrderId, passport.getUserId(), passport.getRole());
        final var responseDto = orderService.denyReturnRequest(requestDto);
        return ResponseEntity.ok(CommonResponse.success(responseDto));
    }

    @PatchMapping("/{orderId}/return/{vendorOrderId}")
    public ResponseEntity<CommonResponse<ReturnOrderResponseDto>> returnOrder(
        @PathVariable final UUID orderId,
        @PathVariable final UUID vendorOrderId,
        HttpServletRequest servletRequest
    ) {
        final Passport passport = passportUtil.getPassportBy(servletRequest);
        final var requestDto = ReturnOrderRequestDto.of(
            orderId, vendorOrderId, passport.getUserId(), passport.getRole());
        final var responseDto = orderService.returnOrder(requestDto);
        return ResponseEntity.ok(CommonResponse.success(responseDto));
    }
}
