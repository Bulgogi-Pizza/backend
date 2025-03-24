package on.logistics.deliverymanagerservice.domain.entity.repository;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.deliverymanagerservice.domain.entity.UserSummary;
import on.logistics.deliverymanagerservice.infrastructure.jpa.UserSummaryJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserSummaryRepositoryImpl implements UserSummaryRepository {

    private final UserSummaryJpaRepository userSummaryJpaRepository;

    @Override
    public UserSummary save(UserSummary userSummary) {
        return userSummaryJpaRepository.save(userSummary);
    }

    @Override
    public Optional<UserSummary> findByUserId(UUID userId) {
        return userSummaryJpaRepository.findById(userId);
    }
}
