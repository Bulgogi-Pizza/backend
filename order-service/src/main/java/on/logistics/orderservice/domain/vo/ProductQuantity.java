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
public class ProductQuantity {

    @Column(name = "quantity", nullable = false)
    private Long value;

    public ProductQuantity(final Long value) {
        validate(value);
        this.value = value;
    }

    private void validate(final Long value) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("Product quantity must be greater than 0");
        }
    }
}
