package on.logistics.slackservice.infrastructure.jpa;

import java.util.UUID;
import on.logistics.slackservice.domain.entity.Slack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlackMessageJpaRepository extends JpaRepository<Slack, UUID> {

}
