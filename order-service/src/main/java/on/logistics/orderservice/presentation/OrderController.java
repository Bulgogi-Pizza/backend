package on.logistics.orderservice.presentation;

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
import on.logistics.orderservice.application.service.dtos.update.UpdateOrderRequestDto;
import on.logistics.orderservice.application.service.dtos.update.UpdateOrderResponseDto;
import on.logistics.orderservice.global.application.dtos.PageDto;
import on.logistics.orderservice.global.enums.AuthRole;
import on.logistics.orderservice.global.presentation.dtos.CommonResponse;
import on.logistics.orderservice.presentation.dtos.delete.DeleteOrderRequestDto;
import on.logistics.orderservice.presentation.dtos.create.CreateOrderRequest;
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

  @PostMapping
  public ResponseEntity<CommonResponse<CreateOrderResponseDto>> createOrder(
      @RequestBody @Valid final CreateOrderRequest request
  ) {
    log.warn("패스포트 토큰을 사용하도록 해야합니다!!");
    final UUID ordererUserId = UUID.fromString("29547e69-f33a-430d-b9a6-75e2b265585c");
    final String ordererUserNickname = "userNickname";
    final var requestDto = CreateOrderRequestDto.from(request, ordererUserId, ordererUserNickname);
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
      final Pageable pageable
  ) {
    log.warn("패스포트 토큰을 사용하도록 해야합니다!!");
    final UUID userId = UUID.fromString("29547e69-f33a-430d-b9a6-75e2b265585c");
    final AuthRole userRole = AuthRole.MASTER;
    final var requestDto = SearchOrderPageRequestDto.from(
        pageable, userId, userRole, ordererUserId, userNickname,
        ordererCompanyId, ordererCompanyName, vendorCompanyId, vendorCompanyName
    );
    final var responseDto = orderService.searchOrderPage(requestDto);
    return ResponseEntity.ok(CommonResponse.success(responseDto));
  }

  @GetMapping("/{orderId}")
  public ResponseEntity<CommonResponse<GetOrderDetailResponseDto>> getOrderDetail(
      @PathVariable final UUID orderId
  ) {
    final var requestDto = GetOrderDetailRequestDto.from(orderId);
    final var responseDto = orderService.getOrderDetail(requestDto);
    return ResponseEntity.ok(CommonResponse.success(responseDto));
  }

  @PatchMapping("/{orderId}")
  public ResponseEntity<CommonResponse<UpdateOrderResponseDto>> updateOrder(
      @PathVariable final UUID orderId,
      @RequestBody @Valid final UpdateOrderRequest request
  ) {
    final var requestDto = UpdateOrderRequestDto.from(request, orderId);
    final var responseDto = orderService.updateOrder(requestDto);
    return ResponseEntity.ok(CommonResponse.success(responseDto));
  }

  @PatchMapping("/{orderId}/cancel/{vendorOrderId}")
  public ResponseEntity<CommonResponse<CancelOrderResponseDto>> cancelVendorOrder(
      @PathVariable final UUID orderId,
      @PathVariable final UUID vendorOrderId
  ) {
    final var requestDto = CancelOrderRequestDto.from(orderId, vendorOrderId);
    final var responseDto = orderService.cancelVendorOrder(requestDto);
    return ResponseEntity.ok(CommonResponse.success(responseDto));
  }

  @DeleteMapping("/{orderId}/{vendorOrderId}")
  public ResponseEntity<CommonResponse<Void>> deleteVendorOrder(
      @PathVariable final UUID orderId,
      @PathVariable final UUID vendorOrderId
  ) {
    final DeleteOrderRequestDto requestDto = DeleteOrderRequestDto.from(orderId, vendorOrderId);
    orderService.deleteVendorOrder(requestDto);
    return ResponseEntity.ok(CommonResponse.success());
  }
}
