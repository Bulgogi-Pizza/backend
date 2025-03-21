package on.logistics.orderservice.global.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.orderservice.global.domain.Passport;
import on.logistics.orderservice.global.exception.passport.PassportException.PassportRetrievalFailedException;
import on.logistics.orderservice.global.exception.passport.PassportException.PassportValidationFailedException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j(topic = "PassportUtil")
public class PassportUtil {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    public Passport getPassportBy(String passportKey) {
        String json = redisTemplate.opsForValue().get(passportKey);

        if (json == null) {
            return null;
        }
        try {
            return objectMapper.readValue(json, Passport.class);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            throw new PassportRetrievalFailedException();
        }
    }

    public Passport getPassportBy(HttpServletRequest httpServletRequest) {
        String passportId = httpServletRequest.getHeader("X-Passport-Id");

        if (passportId == null) {
            throw new PassportValidationFailedException();
        }

        return getPassportBy(passportId);
    }

    public void hasRole(Passport passport, String role) {
        if (!passport.getRole().equals(role)) {
            throw new PassportValidationFailedException();
        }
    }
}

