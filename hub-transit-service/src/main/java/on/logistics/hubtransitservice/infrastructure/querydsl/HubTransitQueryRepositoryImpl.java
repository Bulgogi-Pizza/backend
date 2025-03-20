package on.logistics.hubtransitservice.infrastructure.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.hubtransitservice.domain.entity.HubTransit;
import on.logistics.hubtransitservice.domain.entity.QHubTransit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HubTransitQueryRepositoryImpl implements HubTransitQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<HubTransit> searchHubTransit(UUID deliveryId, String currentHubName,
        Pageable pageable) {
        QHubTransit hubTransit = QHubTransit.hubTransit;
        BooleanBuilder builder = new BooleanBuilder();

        if (deliveryId != null) {
            builder.and(hubTransit.deliveryId.eq(deliveryId));
        }
        if (currentHubName != null && !currentHubName.isEmpty()) {
            builder.and(hubTransit.currentHubName.value.eq(currentHubName));
        }

        long total = queryFactory.selectFrom(hubTransit).where(builder).fetch().size();
        List<HubTransit> results = queryFactory.selectFrom(hubTransit)
            .where(builder)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();
        return new PageImpl<>(results, pageable, total);
    }
}
