package on.logistics.productservice.presentation;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.productservice.application.dto.CreateProductRequestDto;
import on.logistics.productservice.application.dto.UpdateProductRequestDto;
import on.logistics.productservice.application.service.ProductService;
import on.logistics.productservice.global.presentation.dtos.CommonResponse;
import on.logistics.productservice.presentation.dtos.request.CreateProductRequest;
import on.logistics.productservice.presentation.dtos.request.UpdateProductRequest;
import on.logistics.productservice.presentation.dtos.response.CreateProductResponse;
import on.logistics.productservice.presentation.dtos.response.UpdateProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<CommonResponse<CreateProductResponse>> createProduct(
        @Valid @RequestBody CreateProductRequest createProductRequest
    ) {
        CreateProductRequestDto requestDto = CreateProductRequest.from(createProductRequest);
        CreateProductResponse response = productService.createProduct(requestDto);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<UpdateProductResponse>> updateProduct(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateProductRequest updateProductRequest
    ) {
        UpdateProductRequestDto requestDto = UpdateProductRequest.from(id, updateProductRequest);
        UpdateProductResponse response = productService.updateProduct(requestDto);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<Void>> deleteProduct(
        @PathVariable UUID id
    ) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(CommonResponse.success());
    }
}
