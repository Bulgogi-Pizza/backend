package on.logistics.deliverymanagerservice.infrastructure.jpa.querydsl;

import static on.logistics.deliverymanagerservice.domain.entity.QDeliveryManager.deliveryManager;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.deliverymanagerservice.domain.entity.DeliveryManager;
import on.logistics.deliverymanagerservice.domain.entity.DeliveryType;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeliveryManagerRepositoryCustomImpl implements DeliveryManagerRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Integer findMaxSequenceByHubIdAndType(UUID hubId, DeliveryType deliveryType) {
        Integer maxSequence = jpaQueryFactory
            .select(deliveryManager.sequence.max())
            .from(deliveryManager)
            .where(
                deliveryManager.hubId.eq(hubId),
                deliveryManager.type.eq(deliveryType)
            )
            .fetchOne();

        return (maxSequence != null) ? maxSequence : 0;
    }

    @Override
    public Optional<DeliveryManager> findLastAssignedManager(UUID hubId, DeliveryType type) {
        return Optional.ofNullable(
            jpaQueryFactory.selectFrom(deliveryManager)
                .where(
                    deliveryManager.hubId.eq(hubId),
                    deliveryManager.type.eq(type),
                    deliveryManager.lastAssignedAt.isNotNull()
                )
                .orderBy(
                    deliveryManager.lastAssignedAt.desc()
                )
                .fetchFirst()
        );
    }

    @Override
    public Optional<DeliveryManager> findNextDeliveryManager(UUID hubId, Integer lastSequence) {
        return Optional.ofNullable(
            jpaQueryFactory.selectFrom(deliveryManager)
                .where(
                    deliveryManager.hubId.eq(hubId),
                    deliveryManager.sequence.gt(lastSequence)
                )
                .orderBy(deliveryManager.sequence.asc())
                .fetchFirst()
        );
    }

    @Override
    public Optional<DeliveryManager> findFirstByHubIdOrderBySequenceAsc(UUID hubId,
        DeliveryType type) {
        return Optional.ofNullable(
            jpaQueryFactory.selectFrom(deliveryManager)
                .where(
                    deliveryManager.hubId.eq(hubId),
                    deliveryManager.type.eq(type)
                )
                .orderBy(deliveryManager.sequence.asc())
                .fetchFirst()
        );
    }
}
