package on.logistics.orderservice.application.clients.product;

import java.util.UUID;
import on.logistics.orderservice.application.clients.product.dtos.DecreaseProductStockRequestDto;
import on.logistics.orderservice.application.clients.product.dtos.RollbackDecreaseProductStockRequestDto;
import on.logistics.orderservice.application.clients.product.feign.dtos.GetProductResponse;

public interface ProductServiceClient {

  GetProductResponse getProductById(UUID productId);

  void decreaseProductStock(DecreaseProductStockRequestDto requestDto);

  void rollbackDecreaseProductStock(RollbackDecreaseProductStockRequestDto requestDto);
}
