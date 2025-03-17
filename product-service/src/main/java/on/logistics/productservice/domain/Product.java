package on.logistics.productservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import on.logistics.productservice.domain.dto.CreateProductDto;
import on.logistics.productservice.domain.dto.UpdateProductDto;
import on.logistics.productservice.domain.vo.BundleSize;
import on.logistics.productservice.domain.vo.Name;
import on.logistics.productservice.domain.vo.Price;
import on.logistics.productservice.domain.vo.Quantity;
import on.logistics.productservice.global.domain.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SQLRestriction("is_deleted = false")
@SQLDelete(sql = "UPDATE product SET is_deleted = true WHERE id = ?")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "company_id", nullable = false)
    private UUID companyId;

    @Column(name = "managed_hub_id", nullable = false)
    private UUID managedHubId;

    @Embedded
    private Name name;

    @Embedded
    private Quantity quantity;

    @Embedded
    private Price price;

    @Embedded
    private BundleSize bundleSize;

    public static Product create(CreateProductDto createProductDto) {
        return on.logistics.productservice.domain.Product.builder()
            .companyId(createProductDto.companyId())
            .managedHubId(createProductDto.managedHubId())
            .name(new Name(createProductDto.productName()))
            .quantity(new Quantity(createProductDto.productQuantity()))
            .bundleSize(new BundleSize(createProductDto.bundleSize()))
            .build();
    }

    public void update(UpdateProductDto updateProductDto) {
        this.name = name.update(updateProductDto.productName());
        this.quantity = quantity.update(updateProductDto.productQuantity());
        this.price = price.update(updateProductDto.productPrice());
        this.bundleSize = bundleSize.update(updateProductDto.bundleSize());
    }
}
