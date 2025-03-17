package on.logistics.hubservice.domain.repository;

import java.util.Optional;
import java.util.UUID;
import on.logistics.hubservice.domain.entity.Hub;

public interface HubRepository {

    Hub save(Hub hub);

    Optional<Hub> findByIdAndIsDeleted(UUID id, boolean idDeleted);
}
