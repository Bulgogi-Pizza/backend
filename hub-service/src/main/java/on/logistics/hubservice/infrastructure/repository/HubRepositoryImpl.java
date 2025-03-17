package on.logistics.hubservice.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import on.logistics.hubservice.domain.entity.Hub;
import on.logistics.hubservice.domain.repository.HubRepository;
import on.logistics.hubservice.infrastructure.jpa.HubJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HubRepositoryImpl implements HubRepository {

    private final HubJpaRepository hubJpaRepository;

    @Override
    public Hub save(Hub hub) {
        return hubJpaRepository.save(hub);
    }
}
