package on.logistics.productservice.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import on.logistics.companyservice.presentation.dtos.response.CreateProductResponse;
import on.logistics.productservice.application.dto.CreateProductRequestDto;
import on.logistics.productservice.application.service.ProductService;
import on.logistics.productservice.global.presentation.dtos.CommonResponse;
import on.logistics.productservice.presentation.dtos.request.CreateProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
        @Valid @RequestBody CreateProductRequest createProductRequest) {
        CreateProductRequestDto requestDto = CreateProductRequest.from(createProductRequest);
        CreateProductResponse response = productService.createProduct(requestDto);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

}
