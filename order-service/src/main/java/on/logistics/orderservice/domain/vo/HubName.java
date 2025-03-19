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
public class HubName {

  private static final int MAX_LENGTH = 100;

  @Column(name = "hub_name", nullable = false, length = MAX_LENGTH)
  private String value;

  public HubName(final String value) {
    validate(value);
    this.value = value;
  }

  private void validate(final String value) {
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException("Company companyName must not be empty");
    }
    if (value.length() > MAX_LENGTH) {
      throw new IllegalArgumentException("Company companyName must not be longer than " + MAX_LENGTH + " characters");
    }
  }
}
