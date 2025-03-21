package on.logistics.authservice.infrastructure.querydsl;

import java.util.Optional;
import java.util.UUID;
import on.logistics.authservice.domain.entity.Auth;
import org.springframework.stereotype.Repository;

@Repository
public class AuthQueryDslRepositoryImpl implements AuthQueryDslRepository {


    @Override
    public Optional<Auth> findById(UUID uuid) {
        return Optional.empty();
    }
}
