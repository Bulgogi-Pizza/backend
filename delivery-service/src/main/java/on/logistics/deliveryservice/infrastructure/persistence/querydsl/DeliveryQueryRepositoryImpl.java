package on.logistics.deliveryservice.infrastructure.persistence.querydsl;

import static on.logistics.deliveryservice.domain.entity.QDelivery.delivery;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import on.logistics.deliveryservice.application.dtos.request.SearchDeliveryRequestDto;
import on.logistics.deliveryservice.domain.entity.Delivery;
import on.logistics.deliveryservice.global.enums.PageSortBy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeliveryQueryRepositoryImpl implements DeliveryQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Delivery> searchDelivery(SearchDeliveryRequestDto requestDto) {
        BooleanBuilder builder = getSearchDeliveryQuery(requestDto);
        List<Delivery> deliveryList = searchDeliveryList(builder, requestDto.pageable());
        Long total = totalCount(builder);
        return new PageImpl<>(deliveryList, requestDto.pageable(), total);
    }

    private List<Delivery> searchDeliveryList(BooleanBuilder builder, Pageable pageable) {
        OrderSpecifier<?>[] orderSpecifiers = getOrderSpecifiers(pageable);
        return queryFactory.selectFrom(delivery).where(builder).orderBy(orderSpecifiers)
            .offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
    }

    private BooleanBuilder getSearchDeliveryQuery(SearchDeliveryRequestDto cond) {
        BooleanBuilder builder = new BooleanBuilder();
        if (cond.destination() != null) {
            builder.and(delivery.destination.value.contains(cond.destination()));
        }
        if (cond.recipient() != null) {
            builder.and(delivery.recipient.value.contains(cond.recipient()));
        }
        if (cond.status() != null) {
            builder.and(delivery.status.eq(cond.status()));
        }
        if (cond.companyDeliveryManagerId() != null) {
            builder.and(delivery.companyDeliveryManagerId.eq(cond.companyDeliveryManagerId()));
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
                    orderSpecifiers.add(new OrderSpecifier<>(direction, delivery.createdAt));
                case UPDATED_AT ->
                    orderSpecifiers.add(new OrderSpecifier<>(direction, delivery.updatedAt));
                case ID -> orderSpecifiers.add(new OrderSpecifier<>(direction, delivery.id));
            }
        });

        return orderSpecifiers.toArray(new OrderSpecifier[0]);
    }

    private Long totalCount(BooleanBuilder builder) {
        return queryFactory.select(delivery.count()).from(delivery).where(builder).fetchOne();
    }
}
