package on.logistics.slackservice.global.configuration;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.slackservice.global.domain.Passport;
import on.logistics.slackservice.global.utils.PassportUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "loginUserAuditorAware")
@RequiredArgsConstructor
public class JpaAuditingConfig {

    private final HttpServletRequest httpServletRequest;
    private final PassportUtil passportUtil;

    @Bean
    public AuditorAware<UUID> loginUserAuditorAware() {
        return () -> {
            Passport passport = passportUtil.getPassportByHttpServletRequest(httpServletRequest);
            return Optional.of(passport.getUserId());
        };
    }
}
