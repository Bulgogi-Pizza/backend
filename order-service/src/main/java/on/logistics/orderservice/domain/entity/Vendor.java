package on.logistics.orderservice.domain.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import on.logistics.orderservice.domain.entity.dtos.CreateVendorDto;
import on.logistics.orderservice.domain.vo.CompanyName;
import on.logistics.orderservice.domain.vo.HubName;

@Entity
@Table(name = "p_vendors")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class Vendor {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;


  @Column(name = "company_id", nullable = false)
  private UUID companyId;

  @Embedded
  private CompanyName name;

  @Column(name = "hub_id", nullable = false)
  private UUID vendorHubId;

  @Embedded
  private HubName vendorHubName;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "vendor_order_id", nullable = false)
  private VendorOrder vendorOrder;

  public static Vendor create(CreateVendorDto createVendorDto) {
    return Vendor.builder()
        .vendorOrder(createVendorDto.vendorOrder())
        .companyId(createVendorDto.vendorId())
        .name(new CompanyName(createVendorDto.vendorName()))
        .vendorHubName(new HubName(createVendorDto.vendorHubName()))
        .vendorHubId(createVendorDto.vendorHubId())
        .build();
  }

  public static Vendor create(Orderer orderer, VendorOrder vendorOrder) {
    return Vendor.builder()
        .companyId(orderer.getCompanyId())
        .name(orderer.getCompanyName())
        .vendorHubId(orderer.getOrdererHubId())
        .vendorHubName(orderer.getOrdererHubName())
        .vendorOrder(vendorOrder)
        .build();
  }
}
