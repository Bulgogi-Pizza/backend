package on.logistics.slackservice.domain.repository;

import java.util.Optional;
import java.util.UUID;
import on.logistics.slackservice.domain.entity.Slack;

public interface SlackMessageRepository {

    Slack save(Slack slack);

    Optional<Slack> findById(UUID id);
}
