package on.logistics.orderservice.infrastructure.clients.product.feign;

import feign.Response;
import java.util.UUID;
import on.logistics.orderservice.infrastructure.clients.product.feign.dtos.DecreaseProductStockRequest;
import on.logistics.orderservice.infrastructure.clients.product.feign.dtos.IncreaseProductStockRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service")
public interface ProductServiceFeignClient {

    @GetMapping("/api/v1/product/{productId}")
    Response getProductById(@PathVariable UUID productId);

    @PostMapping("/api/v1/product/quantity/{productId}")
    Response decreaseProductStock(
        @PathVariable UUID productId,
        @RequestBody DecreaseProductStockRequest request
    );

    @PostMapping("/api/v1/product/quantity/{productId}/rollback")
    Response increaseProductStock(
        @PathVariable UUID productId,
        @RequestBody IncreaseProductStockRequest request
    );
}
