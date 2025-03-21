package on.logistics.userservice.infrastructure.jpa;

import java.util.Optional;
import java.util.UUID;
import on.logistics.userservice.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, UUID> {

    Optional<User> findById(UUID id);

}
