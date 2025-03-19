package on.logistics.deliveryservice.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import on.logistics.deliveryservice.domain.enums.DeliveryTypeRecord;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Entity(name = "p_delivery_record")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SQLRestriction("is_deleted = false")
@SQLDelete(sql = "UPDATE p_delivery_record SET is_deleted = true WHERE id = ?")
public class DeliveryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Long sequence;

    @Enumerated(EnumType.STRING)
    private DeliveryTypeRecord status;

    @Column(name = "start_hub_id", nullable = false)
    private UUID startHubId;

    @Column(name = "end_hub_id", nullable = false)
    private UUID endHubId;

    @Column(name = "estimated_distance", nullable = false)
    private double estimatedDistance;

    @Column(name = "estimated_duration", nullable = false)
    private Long estimatedDuration;

    @Column(name = "actual_distance")
    private double actualDistance;

    @Column(name = "actual_duration")
    private Long actualDuration;

    @Column(nullable = false)
    private UUID deliveryManagerId;
}
