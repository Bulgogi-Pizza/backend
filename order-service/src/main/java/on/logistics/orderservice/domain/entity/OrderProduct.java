package on.logistics.orderservice.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import on.logistics.orderservice.domain.entity.dtos.CreateOrderProductDto;
import on.logistics.orderservice.domain.vo.ProductName;
import on.logistics.orderservice.domain.vo.ProductPrice;
import on.logistics.orderservice.domain.vo.ProductQuantity;

@Entity
@Table(name = "p_order_products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_order_id", nullable = false)
    private VendorOrder vendorOrder;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Embedded
    private ProductName name;

    @Embedded
    private ProductQuantity quantity;

    @Embedded
    private ProductPrice price;

    public static OrderProduct create(CreateOrderProductDto createOrderProductDto) {
        return OrderProduct.builder()
            .vendorOrder(createOrderProductDto.vendorOrder())
            .productId(createOrderProductDto.productId())
            .name(new ProductName(createOrderProductDto.name()))
            .quantity(new ProductQuantity(createOrderProductDto.quantity()))
            .price(new ProductPrice(createOrderProductDto.price()))
            .build();
    }

    public static OrderProduct create(
        OrderProduct orderProduct,
        VendorOrder returnedVendorOrder
    ) {
        return OrderProduct.builder()
            .vendorOrder(returnedVendorOrder)
            .productId(orderProduct.getProductId())
            .name(orderProduct.getName())
            .quantity(orderProduct.getQuantity())
            .price(orderProduct.getPrice())
            .build();
    }

    public void updateQuantity(Long quantity) {
        this.quantity = new ProductQuantity(quantity);
    }
}
