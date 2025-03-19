package on.logistics.deliveryservice.infrastructure.querydsl;

import static on.logistics.deliveryservice.domain.entity.QDeliveryRecord.deliveryRecord;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import on.logistics.deliveryservice.application.dtos.request.SearchDeliveryRecordRequestDto;
import on.logistics.deliveryservice.domain.entity.DeliveryRecord;
import on.logistics.deliveryservice.global.enums.PageSortBy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeliveryRecordQueryRepositoryImpl implements DeliveryRecordQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<DeliveryRecord> searchDeliveryRecord(SearchDeliveryRecordRequestDto requestDto) {
        BooleanBuilder builder = getSearchDeliveryRecordQuery(requestDto);
        List<DeliveryRecord> deliveryRecordList = searchDeliveryList(builder,
            requestDto.pageable());
        Long total = totalCount(builder);
        return new PageImpl<>(deliveryRecordList, requestDto.pageable(), total);
    }

    private Long totalCount(BooleanBuilder builder) {
        return queryFactory.select(deliveryRecord.count()).from(deliveryRecord).where(builder)
            .fetchOne();
    }

    private List<DeliveryRecord> searchDeliveryList(BooleanBuilder builder, Pageable pageable) {
        OrderSpecifier<?>[] orderSpecifiers = getOrderSpecifiers(pageable);
        return queryFactory.selectFrom(deliveryRecord).where(builder).orderBy(orderSpecifiers)
            .offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
    }

    private BooleanBuilder getSearchDeliveryRecordQuery(SearchDeliveryRecordRequestDto cond) {
        BooleanBuilder builder = new BooleanBuilder();
        if (cond.deliveryManagerId() != null) {
            builder.and(deliveryRecord.deliveryManagerId.eq(cond.deliveryManagerId()));
        }
        if (cond.deliveryId() != null) {
            builder.and(deliveryRecord.delivery.id.eq(cond.deliveryId()));
        }
        if (cond.startHubId() != null) {
            builder.and(deliveryRecord.startHubId.eq(cond.startHubId()));
        }
        if (cond.endHubId() != null) {
            builder.and(deliveryRecord.endHubId.eq(cond.endHubId()));
        }
        return builder;
    }

    private OrderSpecifier<?>[] getOrderSpecifiers(Pageable pageable) {
        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();
        Sort sort = pageable.getSort();

        sort.forEach(order -> {
            String sortBy = order.getProperty();
            Order direction = order.getDirection() == Sort.Direction.ASC ? Order.ASC : Order.DESC;

            switch (PageSortBy.valueOf(sortBy.toUpperCase())) {
                case CREATED_AT ->
                    orderSpecifiers.add(new OrderSpecifier<>(direction, deliveryRecord.createdAt));
                case UPDATED_AT ->
                    orderSpecifiers.add(new OrderSpecifier<>(direction, deliveryRecord.updatedAt));
                case ID -> orderSpecifiers.add(new OrderSpecifier<>(direction, deliveryRecord.id));
            }
        });

        return orderSpecifiers.toArray(new OrderSpecifier[0]);
    }

}
