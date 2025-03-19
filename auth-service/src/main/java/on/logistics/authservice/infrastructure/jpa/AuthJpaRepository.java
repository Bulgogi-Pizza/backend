package on.logistics.authservice.infrastructure.jpa;

import java.util.Optional;
import java.util.UUID;
import on.logistics.authservice.domain.entity.Auth;
import on.logistics.authservice.domain.vo.Username;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthJpaRepository extends JpaRepository<Auth, UUID> {

    Optional<Auth> findByUsername(Username username);

    boolean existsByUsername(Username username);
}
