package on.logistics.hubservice.infrastructure.persistence.jpa;

import java.util.UUID;
import on.logistics.hubservice.domain.entity.HubManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HubManagerJpaRepository extends JpaRepository<HubManager, UUID> {

}
