package on.logistics.orderservice.application.service;

import java.util.UUID;
import on.logistics.orderservice.infrastructure.clients.product.dtos.DecreaseProductStockRequestDto;
import on.logistics.orderservice.infrastructure.clients.product.dtos.RollbackDecreaseProductStockRequestDto;
import on.logistics.orderservice.infrastructure.clients.product.feign.dtos.GetProductResponse;

public interface ProductService {

  GetProductResponse getProductById(UUID productId);

  void decreaseProductStock(DecreaseProductStockRequestDto requestDto);

  void rollbackDecreaseProductStock(RollbackDecreaseProductStockRequestDto requestDto);
}
