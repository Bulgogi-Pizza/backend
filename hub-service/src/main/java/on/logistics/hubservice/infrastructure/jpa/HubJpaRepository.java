package on.logistics.hubservice.infrastructure.jpa;

import java.util.Optional;
import java.util.UUID;
import on.logistics.hubservice.domain.entity.Hub;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HubJpaRepository extends JpaRepository<Hub, UUID> {

    Optional<Hub> findByIdAndIsDeleted(UUID id, Boolean isDeleted);
}
