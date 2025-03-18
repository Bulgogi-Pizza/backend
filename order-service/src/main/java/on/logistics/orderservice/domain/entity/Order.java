package on.logistics.orderservice.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import on.logistics.orderservice.domain.entity.dtos.CreateOrderDto;
import on.logistics.orderservice.global.domain.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "p_orders")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@SQLRestriction("is_deleted = false")
@SQLDelete(sql = "UPDATE p_orders SET is_deleted = true WHERE id = ?")
public class Order extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "productId", updatable = false, nullable = false)
  private UUID id;

  @Column(name = "total_amount", nullable = false)
  private Long totalAmount;

  @Column(name = "destination", nullable = false, length = 500)
  private String destination;

  @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
  private Orderer orderer;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<VendorOrder> vendorOrders;

  public static Order create(CreateOrderDto createOrderDto) {
    return Order.builder()
        .totalAmount(createOrderDto.totalAmount())
        .destination(createOrderDto.destination())
        .build();
  }

  public void addDependencies(Orderer orderer, List<VendorOrder> vendorOrders) {
    this.orderer = orderer;
    this.vendorOrders = vendorOrders;
  }
}
