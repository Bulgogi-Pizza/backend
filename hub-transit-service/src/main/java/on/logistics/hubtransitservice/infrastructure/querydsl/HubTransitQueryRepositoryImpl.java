package on.logistics.hubtransitservice.infrastructure.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
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
    private final QHubTransit hubTransit = QHubTransit.hubTransit;

    @Override
    public Page<HubTransit> searchHubTransit(String keyword, Pageable pageable) {
        BooleanExpression keywordCondition = null;
        if (keyword != null && !keyword.isEmpty()) {
            BooleanExpression currentCondition = hubTransit.currentHubName.value.containsIgnoreCase(
                keyword);
            BooleanExpression nextCondition = hubTransit.nextHubName.value.containsIgnoreCase(
                keyword);
            BooleanExpression deliveryCondition = Expressions.stringTemplate("CAST({0} as varchar)",
                hubTransit.deliveryId).containsIgnoreCase(keyword);
            BooleanExpression deliveryRecordCondition = Expressions.stringTemplate(
                "CAST({0} as varchar)", hubTransit.deliveryRecordId).containsIgnoreCase(keyword);
            BooleanExpression userCondition = Expressions
                .stringTemplate("CAST({0} as varchar)", hubTransit.userId)
                .containsIgnoreCase(keyword);
            BooleanExpression nextDeliveryTypeCondition = hubTransit.nextDeliveryType.stringValue()
                .containsIgnoreCase(
                    keyword);

            keywordCondition = currentCondition
                .or(nextCondition)
                .or(deliveryCondition)
                .or(deliveryRecordCondition)
                .or(userCondition)
                .or(nextDeliveryTypeCondition);
        }

        List<HubTransit> content = queryFactory.selectFrom(hubTransit)
            .where(keywordCondition)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long total = queryFactory.select(hubTransit.count())
            .from(hubTransit)
            .where(keywordCondition)
            .fetchOne();

        return new PageImpl<>(content, pageable, total != null ? total : 0L);
    }
}
