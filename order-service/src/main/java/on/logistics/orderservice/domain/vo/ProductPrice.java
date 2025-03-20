package on.logistics.orderservice.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@EqualsAndHashCode
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductPrice {

  @Column(name = "price", nullable = false)
  private Long value;

  public ProductPrice(final Long value) {
    validate(value);
    this.value = value;
  }

  private void validate(final Long value) {
    if (value == null || value < 0) {
      throw new IllegalArgumentException("Product price must be greater than zero");
    }
  }
}
