package on.logistics.hubservice.domain.entity.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import on.logistics.hubservice.exception.HubException;
import on.logistics.hubservice.exception.HubExceptionCode;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    @Column(name = "address", nullable = false)
    private String value;

    public Address(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(final String value) {
        if (Objects.isNull(value)) {
            throw new HubException(HubExceptionCode.HUB_ADDRESS_IS_NULL);
        }
    }
}
