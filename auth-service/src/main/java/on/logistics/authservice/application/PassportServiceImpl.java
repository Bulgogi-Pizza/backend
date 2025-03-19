package on.logistics.authservice.application;

import io.jsonwebtoken.Claims;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.authservice.infrastructure.feign.UserClientService;
import on.logistics.authservice.infrastructure.feign.dtos.UserFindByIdResponse;
import on.logistics.authservice.infrastructure.security.jwt.JwtUtil;
import on.logistics.authservice.infrastructure.security.passport.Passport;
import on.logistics.authservice.infrastructure.security.passport.PassportUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "PassportServiceImpl")
public class PassportServiceImpl implements PassportService {

    private final PassportUtil passportUtil;
    private final JwtUtil jwtUtil;
    private final UserClientService userClientService;
    @Value("${passport.expiration}")
    private long passportExpirationTime;

    public void createAndStorePassport(String token, UUID userId) {
        UserFindByIdResponse dto = userClientService.findUserById(userId);
        Claims claims = jwtUtil.getUserInfoFromToken(token);
        String username = claims.getSubject();
        String role = claims.get("role", String.class);
        Passport passport = Passport.from(dto, username, role);

        passportUtil.createPassport(token, passport, passportExpirationTime);
    }

    @Override
    public String getPassportIdByToken(String token) {
        return passportUtil.getPassportKeyByToken(token);
    }

    @Override
    public Passport getPassportByToken(String token) {
        return passportUtil.getPassportByToken(token);
    }
}
