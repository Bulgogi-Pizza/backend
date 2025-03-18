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
public class StartHub {

  @Column(name = "start_hub", nullable = false, length = 100)
  private String value;

  public StartHub(final String value) {
    validate(value);
    this.value = value;
  }

  private void validate(String value) {
    throw new UnsupportedOperationException("Unsupported validate");
  }
}
