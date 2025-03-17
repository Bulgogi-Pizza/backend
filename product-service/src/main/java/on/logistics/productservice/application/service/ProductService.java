package on.logistics.productservice.application.service;

import on.logistics.productservice.application.dto.CreateProductRequestDto;
import on.logistics.productservice.application.dto.UpdateProductRequestDto;
import on.logistics.productservice.presentation.dtos.response.CreateProductResponse;
import on.logistics.productservice.presentation.dtos.response.UpdateProductResponse;

public interface ProductService {

    CreateProductResponse createProduct(CreateProductRequestDto requestDto);

    UpdateProductResponse updateProduct(UpdateProductRequestDto requestDto);
}
