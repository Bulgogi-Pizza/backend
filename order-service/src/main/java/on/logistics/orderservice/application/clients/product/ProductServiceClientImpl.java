package on.logistics.orderservice.application.clients.product;

import feign.Response;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.orderservice.application.clients.product.dtos.DecreaseProductStockRequestDto;
import on.logistics.orderservice.application.clients.product.dtos.RollbackDecreaseProductStockRequestDto;
import on.logistics.orderservice.application.clients.product.feign.ProductServiceFeignClient;
import on.logistics.orderservice.application.clients.product.feign.dtos.DecreaseProductStockRequest;
import on.logistics.orderservice.application.clients.product.feign.dtos.GetProductResponse;
import on.logistics.orderservice.application.clients.product.feign.dtos.IncreaseProductStockRequest;
import on.logistics.orderservice.global.utils.FeignClientResponseUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceClientImpl implements ProductServiceClient {

  private final ProductServiceFeignClient productServiceFeignClient;

  @Override
  public GetProductResponse getProductById(UUID productId) {
    log.info("상품 조회 요청");
    Response response = productServiceFeignClient.getProductById(productId);
    return FeignClientResponseUtils.getBody(response, GetProductResponse.class);
  }

  @Override
  public void decreaseProductStock(DecreaseProductStockRequestDto requestDto) {
    log.info("상품 재고 감소 요청");
    var decreaseProductStockRequest = DecreaseProductStockRequest.from(requestDto);
    Response response = productServiceFeignClient.decreaseProductStock(
        requestDto.productId(),
        decreaseProductStockRequest
    );
    FeignClientResponseUtils.validateResponseStatus(response);
  }

  @Override
  public void rollbackDecreaseProductStock(RollbackDecreaseProductStockRequestDto requestDto) {
    log.info("상품 재고 감소 롤백 요청");
    var increaseProductStockRequest = IncreaseProductStockRequest.from(requestDto);
    Response response = productServiceFeignClient.increaseProductStock(
        requestDto.productId(),
        increaseProductStockRequest
    );
    FeignClientResponseUtils.validateResponseStatus(response);
  }
}
