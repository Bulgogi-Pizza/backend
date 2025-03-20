package on.logistics.deliveryservice.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import on.logistics.deliveryservice.exception.DeliveryException;
import on.logistics.deliveryservice.exception.DeliveryExceptionCode;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recipient {

    @Column(name = "recipient", nullable = false)
    private String value;

    public Recipient(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(final String value) {
        if (Objects.isNull(value)) {
            throw new DeliveryException(DeliveryExceptionCode.DELIVERY_RECIPIENT_IS_NULL);
        }
    }

    public Recipient update(final String value) {
        return new Recipient(value);
    }
}
