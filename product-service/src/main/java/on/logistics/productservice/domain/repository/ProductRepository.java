package on.logistics.productservice.domain.repository;

import java.util.Optional;
import java.util.UUID;
import on.logistics.productservice.domain.Product;

public interface ProductRepository {

    Product save(Product product);

    Optional<Product> findById(UUID uuid);
}
