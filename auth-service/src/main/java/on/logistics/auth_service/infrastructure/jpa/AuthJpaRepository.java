package on.logistics.auth_service.infrastructure.jpa;

import java.util.UUID;
import on.logistics.auth_service.domain.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthJpaRepository extends JpaRepository<Auth, UUID> {

}
