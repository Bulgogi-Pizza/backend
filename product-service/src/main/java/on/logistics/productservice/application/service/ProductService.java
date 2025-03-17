package on.logistics.productservice.application.service;

import java.util.UUID;
import on.logistics.productservice.application.dto.CreateProductRequestDto;
import on.logistics.productservice.application.dto.SearchProductRequestDto;
import on.logistics.productservice.application.dto.UpdateProductRequestDto;
import on.logistics.productservice.global.application.dtos.PageDto;
import on.logistics.productservice.presentation.dtos.response.CreateProductResponse;
import on.logistics.productservice.presentation.dtos.response.GetProductResponse;
import on.logistics.productservice.presentation.dtos.response.SearchProductResponse;
import on.logistics.productservice.presentation.dtos.response.UpdateProductResponse;

public interface ProductService {

    CreateProductResponse createProduct(CreateProductRequestDto requestDto);

    PageDto<SearchProductResponse> searchProduct(SearchProductRequestDto requestDto);

    GetProductResponse getProduct(UUID id);

    UpdateProductResponse updateProduct(UpdateProductRequestDto requestDto);

    void deleteProduct(UUID id);

}
