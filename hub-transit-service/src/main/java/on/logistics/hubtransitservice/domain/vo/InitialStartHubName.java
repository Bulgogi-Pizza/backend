package on.logistics.hubtransitservice.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import on.logistics.hubtransitservice.exception.HubTransitException;
import on.logistics.hubtransitservice.exception.HubTransitExceptionCode;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InitialStartHubName {

    public static final int MAX_LENGTH = 100;

    @Column(name = "initial_start_hub_name", nullable = false, length = MAX_LENGTH)
    private String value;

    public InitialStartHubName(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(final String value) {
        if (Objects.isNull(value) || value.isBlank()) {
            throw new HubTransitException(HubTransitExceptionCode.HUB_NAME_IS_NULL);
        }
        if (value.length() > MAX_LENGTH) {
            throw new HubTransitException(HubTransitExceptionCode.MAX_LENGTH_EXCEEDED);
        }
    }

}
