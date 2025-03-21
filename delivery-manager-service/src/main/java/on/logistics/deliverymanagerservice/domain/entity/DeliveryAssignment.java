package on.logistics.deliverymanagerservice.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import on.logistics.deliverymanagerservice.global.domain.BaseEntity;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "p_delivery_assignment")
public class DeliveryAssignment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID hubId;

    @Column(nullable = false)
    private UUID deliveryManagerId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LogisticsStatus status;

    @Builder
    private DeliveryAssignment(UUID hubId, UUID deliveryManagerId, LogisticsStatus status) {
        this.hubId = hubId;
        this.deliveryManagerId = deliveryManagerId;
        this.status = status;
    }

    public static DeliveryAssignment create(UUID hubId, UUID deliveryManagerId) {
        return DeliveryAssignment.builder()
            .hubId(hubId)
            .deliveryManagerId(deliveryManagerId)
            .status(LogisticsStatus.TAKE_OVER)
            .build();
    }
}
