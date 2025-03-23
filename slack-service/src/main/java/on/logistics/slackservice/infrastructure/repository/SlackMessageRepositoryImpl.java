package on.logistics.slackservice.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.slackservice.domain.entity.Slack;
import on.logistics.slackservice.domain.repository.SlackMessageRepository;
import on.logistics.slackservice.infrastructure.jpa.SlackMessageJpaRepository;
import on.logistics.slackservice.infrastructure.querydsl.SlackMessageQueryRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SlackMessageRepositoryImpl implements SlackMessageRepository {

    private final SlackMessageJpaRepository slackMessageJpaRepository;
    private final SlackMessageQueryRepositoryImpl slackMessageQueryRepository;

    @Override
    public Slack save(Slack slack) {
        return slackMessageJpaRepository.save(slack);
    }

    @Override
    public Optional<Slack> findById(UUID id) {
        return slackMessageJpaRepository.findById(id);
    }
}
