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
public class Destination {

    @Column(name = "destination", nullable = false)
    private String value;

    public Destination(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(final String value) {
        if (Objects.isNull(value)) {
            throw new DeliveryException(DeliveryExceptionCode.DELIVERY_DESTINATION_IS_NULL);
        }
    }

    public Destination update(final String value) {
        return new Destination(value);
    }
}
