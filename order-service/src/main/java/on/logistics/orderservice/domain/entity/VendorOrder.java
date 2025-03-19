package on.logistics.orderservice.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import on.logistics.orderservice.domain.entity.dtos.CreateVendorOrderDto;
import on.logistics.orderservice.domain.enums.OrderStatus;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "p_vendor_orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@SQLRestriction("is_deleted = false")
@SQLDelete(sql = "UPDATE p_vendor_orders SET is_deleted = true WHERE id = ?")
public class VendorOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "total_amount", nullable = false)
  private Long totalAmount;

  @Column(name = "status", nullable = false)
  private OrderStatus status;

  @Column(name = "arrival_deadline", nullable = false)
  private LocalDateTime arrivalDeadline;

  @Column(name = "shipping_deadline")
  private LocalDateTime shippingDeadline;

  @Column(name = "shipped_at")
  private LocalDateTime shippedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  @OneToOne(mappedBy = "vendorOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Vendor vendor;

  @OneToMany(mappedBy = "vendorOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<OrderProduct> orderProducts;

  @Column(name = "is_deleted")
  private boolean isDeleted = false;

  public static VendorOrder create(CreateVendorOrderDto createVendorOrderDto) {
    return VendorOrder.builder()
        .order(createVendorOrderDto.order())
        .totalAmount(createVendorOrderDto.totalAmount())
        .status(OrderStatus.ORDER_CREATED)
        .arrivalDeadline(createVendorOrderDto.arrivalDeadline())
        .build();
  }

  public void addDependencies(Vendor vendor, List<OrderProduct> orderProducts) {
    this.vendor = vendor;
    this.orderProducts = orderProducts;
  }

  public void updateShippingDeadline(LocalDateTime shippingDeadline) {
    this.shippingDeadline = shippingDeadline;
  }

  public Long getAmountByVendor() {
    return orderProducts.stream()
        .mapToLong(orderProduct -> orderProduct.getPrice().getValue())
        .sum();
  }

  public void updateArrivalDeadline(LocalDateTime arrivalDeadline) {
    this.arrivalDeadline = arrivalDeadline;
  }

  public void cancel() {
    this.status = OrderStatus.CANCELLED;
  }
}
