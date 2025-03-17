package on.logistics.productservice.application.service;

import on.logistics.companyservice.presentation.dtos.response.CreateProductResponse;
import on.logistics.productservice.application.dto.CreateProductRequestDto;

public interface ProductService {

    CreateProductResponse createProduct(CreateProductRequestDto requestDto);
}
