package on.logistics.deliverymanagerservice.global.auditing;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.deliverymanagerservice.global.domain.Passport;
import on.logistics.deliverymanagerservice.global.exception.passport.PassportException;
import on.logistics.deliverymanagerservice.global.exception.passport.PassportExceptionCode;
import on.logistics.deliverymanagerservice.global.util.PassportUtil;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginUserAuditorAware implements AuditorAware<UUID> {

    private final HttpServletRequest request;
    private final PassportUtil passportUtil;

    @Override
    public Optional<UUID> getCurrentAuditor() {
        String passportId = request.getHeader("X-Passport-Id");
        if (passportId == null) {
            throw new PassportException(PassportExceptionCode.PASSPORT_VALIDATION_FAILED);
        }

        Passport passport = passportUtil.getPassportByHttpServletRequest(request);

        return Optional.of(passport.getUserId());
    }

}
