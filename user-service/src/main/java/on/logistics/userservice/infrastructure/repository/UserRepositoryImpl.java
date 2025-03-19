package on.logistics.userservice.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.userservice.domain.entity.User;
import on.logistics.userservice.domain.repository.UserRepository;
import on.logistics.userservice.infrastructure.jpa.UserJpaRepository;
import on.logistics.userservice.infrastructure.querydsl.UserQueryRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserQueryRepository userQueryRepository;


    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userJpaRepository.delete(user);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userJpaRepository.findById(id);
    }
}
