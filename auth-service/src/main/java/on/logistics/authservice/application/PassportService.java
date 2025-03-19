package on.logistics.authservice.application;

import java.util.UUID;

public interface PassportService {

    void createAndStorePassport(String token, UUID userId);
}
