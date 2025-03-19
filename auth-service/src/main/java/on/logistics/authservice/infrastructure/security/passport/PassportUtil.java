package on.logistics.authservice.infrastructure.security.passport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.authservice.exception.PassportException;
import on.logistics.authservice.exception.PassportExceptionCode;
import on.logistics.authservice.infrastructure.security.hash.HashUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j(topic = "PassportUtil")
public class PassportUtil {

    private static final String PASSPORT_KEY_PREFIX = "passport:";
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;
    private final HashUtil hashUtil;

    private String getPassportKey(String token) {
        String key = hashUtil.sha256Hex(token);
        return PASSPORT_KEY_PREFIX + key;
    }

    public void createPassport(String token, Passport passport, long ttlSeconds) {
        try {
            String json = objectMapper.writeValueAsString(passport);
            redisTemplate.opsForValue().set(getPassportKey(token), json, ttlSeconds,
                TimeUnit.SECONDS);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            throw new PassportException(PassportExceptionCode.PASSPORT_CREATION_FAILED);
        }
    }

    public Passport getPassportByToken(String token) {
        String json = redisTemplate.opsForValue().get(getPassportKey(token));
        if (json == null) {
            return null;
        }
        try {
            return objectMapper.readValue(json, Passport.class);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            throw new PassportException(PassportExceptionCode.PASSPORT_CREATION_FAILED);
        }
    }

    public String getPassportKeyByToken(String token) {
        return getPassportKey(token);
    }

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

    public void updatePassport(String token, Passport updatedPassport, long ttlSeconds) {
        createPassport(token, updatedPassport, ttlSeconds);
    }

    public void deletePassport(String token) {
        redisTemplate.delete(getPassportKey(token));
    }

    public boolean isAuthenticated(String token) {
        return getPassportByToken(token) != null;
    }
}
