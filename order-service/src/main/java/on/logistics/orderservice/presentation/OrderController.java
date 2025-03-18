package on.logistics.orderservice.presentation;

import feign.Response;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.orderservice.application.OrderService;
import on.logistics.orderservice.application.clients.product.feign.ProductServiceFeignClient;
import on.logistics.orderservice.application.dtos.create.CreateOrderRequestDto;
import on.logistics.orderservice.application.dtos.create.CreateOrderResponseDto;
import on.logistics.orderservice.global.presentation.dtos.CommonResponse;
import on.logistics.orderservice.global.utils.FeignClientResponseUtils;
import on.logistics.orderservice.presentation.dtos.create.CreateOrderRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    final var requestDto = CreateOrderRequestDto.from(request);
    final var responseDto = orderService.createOrder(requestDto);
    return ResponseEntity.ok(CommonResponse.success(responseDto));
  }
}
