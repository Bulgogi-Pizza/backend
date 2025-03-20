package on.logistics.authservice.application;

import java.util.UUID;
import on.logistics.authservice.infrastructure.security.passport.Passport;

public interface PassportService {

    void createAndStorePassport(String token, UUID userId);

    String getPassportIdByToken(String token);

    Passport getPassportByToken(String token);
}
