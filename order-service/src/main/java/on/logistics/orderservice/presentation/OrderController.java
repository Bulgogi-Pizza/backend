package on.logistics.orderservice.presentation;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.orderservice.application.service.OrderService;
import on.logistics.orderservice.application.service.dtos.create.CreateOrderRequestDto;
import on.logistics.orderservice.application.service.dtos.create.CreateOrderResponseDto;
import on.logistics.orderservice.application.service.dtos.get.all.GetOrderPageByOrdererUserIdResponseDto;
import on.logistics.orderservice.application.service.dtos.get.all.GetOrdererPageByOrdererUserIdRequestDto;
import on.logistics.orderservice.global.application.dtos.PageDto;
import on.logistics.orderservice.global.enums.AuthRole;
import on.logistics.orderservice.global.presentation.dtos.CommonResponse;
import on.logistics.orderservice.presentation.dtos.create.CreateOrderRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
  public ResponseEntity<CommonResponse<PageDto<GetOrderPageByOrdererUserIdResponseDto>>> getOrderPageByOrdererUserId(
      @RequestParam(required = false) UUID userId,
      final Pageable pageable
  ) {
    log.warn("패스포트 토큰을 사용하도록 해야합니다!!");
    UUID ordererUserId = UUID.fromString("29547e69-f33a-430d-b9a6-75e2b265585c");
    final AuthRole ordererUserRole = AuthRole.MASTER;
    if (AuthRole.isAllowedSearchingOrderPageByUserId(ordererUserRole) && userId != null) {
      log.info("마스터 권한으로 다른 사용자의 주문 목록을 조회합니다. userId={}", userId);
      ordererUserId = userId;
    }
    final var requestDto = GetOrdererPageByOrdererUserIdRequestDto.from(ordererUserId, pageable);
    final var responseDto = orderService.getOrderPageByOrdererUserId(requestDto);
    return ResponseEntity.ok(CommonResponse.success(responseDto));
  }
}
