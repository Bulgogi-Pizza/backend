package on.logistics.auth_service.domain.repository;

import java.util.Optional;
import java.util.UUID;
import on.logistics.auth_service.domain.entity.Auth;
import org.springframework.stereotype.Repository;

public interface AuthRepository {

    Auth save(Auth auth);

    Optional<Auth> findById(UUID uuid);
}
