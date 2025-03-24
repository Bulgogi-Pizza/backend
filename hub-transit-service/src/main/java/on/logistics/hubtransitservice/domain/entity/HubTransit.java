package on.logistics.hubtransitservice.domain.entity;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import on.logistics.hubtransitservice.domain.dtos.CreateHubTransitDto;
import on.logistics.hubtransitservice.domain.enums.DeliveryType;
import on.logistics.hubtransitservice.domain.vo.CurrentHubName;
import on.logistics.hubtransitservice.domain.vo.NextHubName;
import on.logistics.hubtransitservice.global.domain.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "p_hub_transit")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SQLRestriction("is_deleted = false")
@SQLDelete(sql = "UPDATE p_hub_transit SET is_deleted = true WHERE id = ?")
public class HubTransit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "delivery_id", nullable = false)
    private UUID deliveryId;

    @Column(name = "delivery_record_id", nullable = false)
    private UUID deliveryRecordId;

    @Column(name = "current_hub_id", nullable = false)
    private UUID currentHubId;

    @Embedded
    private CurrentHubName currentHubName;

    @Column(name = "next_hub_id", nullable = false)
    private UUID nextHubId;

    @Embedded
    private NextHubName nextHubName;

    @Enumerated(EnumType.STRING)
    @Column(name = "next_delivery_type", nullable = false)
    private DeliveryType nextDeliveryType;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "route_snapshot", nullable = false, columnDefinition = "jsonb")
    @Type(JsonBinaryType.class)
    private String routeSnapshot;


    public static HubTransit create(CreateHubTransitDto dto) {
        return HubTransit.builder()
            .deliveryId(dto.deliveryId())
            .deliveryRecordId(dto.deliveryRecordId())
            .currentHubId(dto.currentHubId())
            .currentHubName(new CurrentHubName(dto.currentHubName()))
            .nextHubId(dto.nextHubId())
            .nextHubName(new NextHubName(dto.nextHubName()))
            .nextDeliveryType(dto.nextDeliveryType())
            .userId(dto.userId())
            .routeSnapshot(dto.routeSnapshot())
            .build();
    }

    public void updateDeliveryManagerId(UUID userId) {
        this.userId = userId;
    }

    public void deleteSoftly() {
        super.deleteSoftly();
    }

}
