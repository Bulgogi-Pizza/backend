package on.logistics.orderservice.presentation;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.orderservice.application.service.OrderService;
import on.logistics.orderservice.application.service.dtos.create.CreateOrderRequestDto;
import on.logistics.orderservice.application.service.dtos.create.CreateOrderResponseDto;
import on.logistics.orderservice.global.presentation.dtos.CommonResponse;
import on.logistics.orderservice.presentation.dtos.create.CreateOrderRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    final UUID ordererUserId = UUID.randomUUID();
    final String ordererUserNickname = "userNickname";
    final var requestDto = CreateOrderRequestDto.from(request, ordererUserId, ordererUserNickname);
    final var responseDto = orderService.createOrder(requestDto);
    return ResponseEntity.ok(CommonResponse.success(responseDto));
  }
}
