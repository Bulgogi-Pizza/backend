package on.logistics.deliverymanagerservice.domain.entity.repository;

import java.util.Optional;
import java.util.UUID;
import on.logistics.deliverymanagerservice.domain.entity.UserSummary;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSummaryRepository {

    UserSummary save(UserSummary userSummary);

    Optional<UserSummary> findByUserId(UUID userId);
}
