package on.logistics.productservice.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.productservice.application.dto.CreateProductRequestDto;
import on.logistics.productservice.application.dto.UpdateProductRequestDto;
import on.logistics.productservice.domain.Product;
import on.logistics.productservice.domain.dto.CreateProductDto;
import on.logistics.productservice.domain.dto.UpdateProductDto;
import on.logistics.productservice.domain.repository.ProductRepository;
import on.logistics.productservice.exception.ProductException;
import on.logistics.productservice.exception.ProductExceptionCode;
import on.logistics.productservice.presentation.dtos.response.CreateProductResponse;
import on.logistics.productservice.presentation.dtos.response.UpdateProductResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public CreateProductResponse createProduct(CreateProductRequestDto requestDto) {

        // todo : 업체 관리자인지 아닌지 확인하는 로직 필요
        CreateProductDto createProductDto = CreateProductDto.from(requestDto);

        Product product = Product.create(createProductDto);
        Product saved = productRepository.save(product);
        return CreateProductResponse.of(saved.getId());
    }

    @Override
    @Transactional
    public UpdateProductResponse updateProduct(UpdateProductRequestDto requestDto) {

        // todo : 업체 관리자인지 아닌지 확인하는 로직 필요
        UpdateProductDto updateProductDto = UpdateProductDto.from(requestDto);
        Product product = getOrElseThrow(updateProductDto);
        product.update(updateProductDto);
        return UpdateProductResponse.of(product.getId());
    }

    private Product getOrElseThrow(UpdateProductDto updateProductDto) {
        return productRepository.findById(updateProductDto.productId())
            .orElseThrow(() -> new ProductException(
                ProductExceptionCode.PRODUCT_IS_NOT_FOUND));
    }
}