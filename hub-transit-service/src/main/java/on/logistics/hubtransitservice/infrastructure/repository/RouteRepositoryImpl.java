package on.logistics.hubtransitservice.infrastructure.repository;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import on.logistics.hubtransitservice.domain.entity.Route;
import on.logistics.hubtransitservice.domain.repository.RouteRepository;
import on.logistics.hubtransitservice.infrastructure.jpa.RouteJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RouteRepositoryImpl implements RouteRepository {

    private final RouteJpaRepository routeJpaRepository;

    @Override
    public Optional<Route> findByStartHubNameAndEndHubName(String startHubName, String endHubName) {
        return routeJpaRepository.findByStartHubNameAndEndHubName(startHubName, endHubName);
    }
}
