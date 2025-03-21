package on.logistics.userservice.global.auditing;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.userservice.global.domain.Passport;
import on.logistics.userservice.global.exception.passport.PassportException;
import on.logistics.userservice.global.exception.passport.PassportExceptionCode;
import on.logistics.userservice.global.util.PassportUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginUserAuditorAware implements AuditorAware<UUID> {

    private final HttpServletRequest request;
    private final PassportUtil passportUtil;
    @Value("${user.unauthorized.uri}")
    private String unauthorizedUri;

    @Override
    public Optional<UUID> getCurrentAuditor() {
        String passportId = request.getHeader("X-Passport-Id");

        if (request.getRequestURI().equals(unauthorizedUri) && request.getMethod().equals("POST")) {
            return Optional.empty();
        }

        if (passportId == null) {
            throw new PassportException(PassportExceptionCode.PASSPORT_VALIDATION_FAILED);
        }

        Passport passport = passportUtil.getPassportByHttpServletRequest(request);

        return Optional.of(passport.getUserId());
    }

}