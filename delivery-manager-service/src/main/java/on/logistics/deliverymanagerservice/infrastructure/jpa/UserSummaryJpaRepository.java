package on.logistics.deliverymanagerservice.infrastructure.jpa;

import java.util.UUID;
import on.logistics.deliverymanagerservice.domain.entity.UserSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSummaryJpaRepository extends JpaRepository<UserSummary, UUID> {

}
