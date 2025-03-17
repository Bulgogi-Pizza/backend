package on.logistics.productservice.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.companyservice.presentation.dtos.response.CreateProductResponse;
import on.logistics.productservice.application.dto.CreateProductRequestDto;
import on.logistics.productservice.domain.Product;
import on.logistics.productservice.domain.dto.CreateProductDto;
import on.logistics.productservice.domain.repository.ProductRepository;
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
        CreateProductDto createProductDto = CreateProductDto.from(requestDto.productName(),
            requestDto.companyId(), requestDto.managedHubId(),
            requestDto.productQuantity(), requestDto.productPrice(), requestDto.bundleSize());

        Product product = Product.create(createProductDto);
        Product saved = productRepository.save(product);
        return CreateProductResponse.of(saved.getId());
    }
}