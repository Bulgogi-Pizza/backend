package on.logistics.hubtransitservice.infrastructure.jpa;

import java.util.UUID;
import on.logistics.hubtransitservice.domain.entity.HubTransit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HubTransitJpaRepository extends JpaRepository<HubTransit, UUID> {

}
