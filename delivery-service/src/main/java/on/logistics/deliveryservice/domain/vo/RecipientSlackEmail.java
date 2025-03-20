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
public class RecipientSlackEmail {

    @Column(name = "recipient_slack_email", nullable = false)
    private String value;

    public RecipientSlackEmail(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(final String value) {
        if (Objects.isNull(value)) {
            throw new DeliveryException(
                DeliveryExceptionCode.DELIVERY_RECIPIENT_SLACK_EMAIL_IS_NULL);
        }
    }

    public RecipientSlackEmail update(final String value) {
        return new RecipientSlackEmail(value);
    }
}
