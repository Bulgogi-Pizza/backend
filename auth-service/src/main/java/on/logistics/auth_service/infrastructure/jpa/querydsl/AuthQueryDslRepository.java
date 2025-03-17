package on.logistics.auth_service.infrastructure.jpa.querydsl;

import java.util.Optional;
import java.util.UUID;
import on.logistics.auth_service.domain.entity.Auth;

public interface AuthQueryDslRepository {

    Optional<Auth> findById(UUID uuid);
}
