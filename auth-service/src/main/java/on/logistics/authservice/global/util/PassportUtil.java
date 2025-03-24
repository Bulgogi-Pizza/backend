package on.logistics.authservice.global.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.authservice.exception.PassportException;
import on.logistics.authservice.exception.PassportExceptionCode;
import on.logistics.authservice.infrastructure.security.passport.Passport;
import org.springframework.data.redis.core.RedisTemplate;

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

}
