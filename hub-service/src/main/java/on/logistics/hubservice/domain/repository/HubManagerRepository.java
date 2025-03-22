package on.logistics.hubservice.domain.repository;

import on.logistics.hubservice.domain.entity.HubManager;
import org.springframework.stereotype.Repository;

@Repository
public interface HubManagerRepository {

    HubManager save(HubManager hubManager);
}
