package on.logistics.hubservice.global.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.hubservice.global.domain.Passport;
import on.logistics.hubservice.global.exception.passport.PassportException;
import on.logistics.hubservice.global.exception.passport.PassportExceptionCode;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j(topic = "PassportUtil")
public class PassportUtil {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    public Passport getPassportByKey(String passportKey) {
        String json = redisTemplate.opsForValue().get(passportKey);

        if (json == null) {
            return null;
        }
        try {
            return objectMapper.readValue(json, Passport.class);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            throw new PassportException(PassportExceptionCode.PASSPORT_RETRIEVAL_FAILED);
        }
    }

    public Passport getPassportByHttpServletRequest(HttpServletRequest httpServletRequest) {
        String passportId = httpServletRequest.getHeader("X-Passport-Id");

        if (passportId == null) {
            throw new PassportException(PassportExceptionCode.PASSPORT_VALIDATION_FAILED);
        }

        return getPassportByKey(passportId);
    }

}
