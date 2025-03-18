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
public class ProductName {

  private static final int MAX_LENGTH = 100;

  @Column(name = "name", nullable = false, length = MAX_LENGTH)
  private String value;

  public ProductName(final String value) {
    validate(value);
    this.value = value;
  }

  private void validate(final String value) {
    throw new UnsupportedOperationException("Unsupported validate");
  }
}
