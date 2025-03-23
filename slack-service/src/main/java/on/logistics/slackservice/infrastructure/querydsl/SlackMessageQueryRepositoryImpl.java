package on.logistics.slackservice.infrastructure.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SlackMessageQueryRepositoryImpl implements SlackMessageQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

}
