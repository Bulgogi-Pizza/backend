package on.logistics.hubservice.domain.repository;

import lombok.RequiredArgsConstructor;
import on.logistics.hubservice.domain.entity.HubManager;
import on.logistics.hubservice.infrastructure.persistence.jpa.HubManagerJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HubManagerRepositoryImpl implements HubManagerRepository {

    private final HubManagerJpaRepository hubManagerJpaRepository;

    @Override
    public HubManager save(HubManager hubManager) {
        return hubManagerJpaRepository.save(hubManager);
    }
}
