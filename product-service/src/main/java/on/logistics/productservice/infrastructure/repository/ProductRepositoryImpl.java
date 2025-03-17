package on.logistics.productservice.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import on.logistics.productservice.domain.Product;
import on.logistics.productservice.domain.repository.ProductRepository;
import on.logistics.productservice.infrastructure.jpa.ProductJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public Product save(Product product) {
        return productJpaRepository.save(product);
    }
}
