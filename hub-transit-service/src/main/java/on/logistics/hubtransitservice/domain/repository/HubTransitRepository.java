package on.logistics.hubtransitservice.domain.repository;

import java.util.Optional;
import java.util.UUID;
import on.logistics.hubtransitservice.domain.entity.HubTransit;

public interface HubTransitRepository {

    HubTransit save(HubTransit hubTransit);

    Optional<HubTransit> findById(UUID transitId);

}
