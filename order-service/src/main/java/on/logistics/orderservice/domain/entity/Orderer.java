package on.logistics.orderservice.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
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
import on.logistics.orderservice.domain.entity.dtos.CreateOrdererDto;
import on.logistics.orderservice.domain.vo.CompanyName;
import on.logistics.orderservice.domain.vo.UserNickname;

@Entity
@Table(name = "p_orderers")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class Orderer {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "company_id", updatable = false, nullable = false)
  private UUID companyId;

  @Embedded
  private CompanyName companyName;

  @Column(name = "user_id", updatable = false, nullable = false)
  private UUID userId;

  @Embedded
  private UserNickname userNickname;

  @OneToOne
  @JoinColumn(name = "order_id", updatable = false, nullable = false)
  private Order order;

  public static Orderer create(CreateOrdererDto requestDto) {
    return Orderer.builder()
        .order(requestDto.order())
        .companyId(requestDto.companyId())
        .userId(requestDto.userId())
        .companyName(new CompanyName(requestDto.companyName()))
        .userNickname(new UserNickname(requestDto.userNickname()))
        .build();
  }
}
