package on.logistics.hubtransitservice.infrastructure.jpa;

import java.util.Optional;
import java.util.UUID;
import on.logistics.hubtransitservice.domain.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteJpaRepository extends JpaRepository<Route, UUID> {

    Optional<Route> findByStartHubNameAndEndHubName(String startHubName,
        String endHubName);

}
