package on.logistics.hubtransitservice.domain.repository;

import java.util.Optional;
import on.logistics.hubtransitservice.domain.entity.Route;

public interface RouteRepository {

    Optional<Route> findByStartHubNameAndEndHubName(String startHubName, String endHubName);

}
