package on.logistics.auth_service.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.auth_service.domain.entity.Auth;
import on.logistics.auth_service.domain.repository.AuthRepository;
import on.logistics.auth_service.infrastructure.jpa.AuthJpaRepository;
import on.logistics.auth_service.infrastructure.jpa.querydsl.AuthQueryDslRepository;
import on.logistics.auth_service.infrastructure.jpa.querydsl.AuthQueryDslRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuthRepositoryImpl implements AuthRepository, AuthQueryDslRepository {

    private final AuthJpaRepository authJpaRepository;
    private final AuthQueryDslRepositoryImpl authQueryDslRepository;

    @Override
    public Auth save(Auth auth) {
        return authJpaRepository.save(auth);
    }

    @Override
    public Optional<Auth> findById(UUID uuid) {
        return authQueryDslRepository.findById(uuid);
    }
}
