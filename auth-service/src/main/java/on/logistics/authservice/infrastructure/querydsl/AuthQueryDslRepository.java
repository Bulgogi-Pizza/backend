package on.logistics.authservice.infrastructure.querydsl;

import java.util.Optional;
import java.util.UUID;
import on.logistics.authservice.domain.entity.Auth;

public interface AuthQueryDslRepository {

    Optional<Auth> findById(UUID uuid);
}
