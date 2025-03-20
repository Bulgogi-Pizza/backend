package on.logistics.deliveryservice.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import on.logistics.deliveryservice.domain.dtos.CreateDeliveryRecordDto;
import on.logistics.deliveryservice.domain.enums.DeliveryRecordStatus;
import on.logistics.deliveryservice.global.domain.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Entity(name = "p_delivery_record")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SQLRestriction("is_deleted = false")
@SQLDelete(sql = "UPDATE p_delivery_record SET is_deleted = true WHERE id = ?")
public class DeliveryRecord extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Column(nullable = false)
    private Long sequence;

    @Enumerated(EnumType.STRING)
    private DeliveryRecordStatus status;

    @Column(name = "start_hub_id", nullable = false)
    private UUID startHubId;

    @Column(name = "end_hub_id", nullable = false)
    private UUID endHubId;

    @Column(name = "estimated_distance", nullable = false)
    private Long estimatedDistance;

    @Column(name = "estimated_duration", nullable = false)
    private Long estimatedDuration;

    @Column(name = "actual_distance")
    private Long actualDistance;

    @Column(name = "actual_duration")
    private Long actualDuration;

    @Column(nullable = false)
    private UUID deliveryManagerId;

    public static DeliveryRecord create(CreateDeliveryRecordDto createEntityDto,
        Delivery delivery) {
        return DeliveryRecord.builder().sequence(createEntityDto.sequence()).delivery(delivery)
            .status(createEntityDto.status()).startHubId(createEntityDto.startHubId())
            .endHubId(createEntityDto.endHubId())
            .estimatedDistance(createEntityDto.estimatedDistance())
            .estimatedDuration(createEntityDto.estimatedDuration())
            .deliveryManagerId(createEntityDto.deliveryManagerId()).build();
    }

    public void update(Long actualDistance, Long actualDuration) {
        this.actualDistance = actualDistance;
        this.actualDuration = actualDuration;
    }

    public void updateStatus(DeliveryRecordStatus deliveryRecordStatus) {
        this.status = deliveryRecordStatus;
    }
}
