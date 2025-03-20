package on.logistics.hubtransitservice.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
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
import on.logistics.hubtransitservice.domain.vo.CurrentHubName;
import on.logistics.hubtransitservice.domain.vo.InitialEndHubName;
import on.logistics.hubtransitservice.domain.vo.InitialStartHubName;
import on.logistics.hubtransitservice.domain.vo.NextHubName;
import on.logistics.hubtransitservice.global.domain.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

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

    @Column(name = "current_hub_id", nullable = false)
    private UUID currentHubId;

    @Embedded
    private CurrentHubName currentHubName;

    @Column(name = "next_hub_id", nullable = false)
    private UUID nextHubId;

    @Embedded
    private NextHubName nextHubName;

    @Column(name = "delivery_id", nullable = false)
    private UUID deliveryId;

    @Column(name = "delivery_manager_id", nullable = false)
    private UUID deliveryManagerId;

    @Embedded
    private InitialStartHubName initialStartHubName;

    @Embedded
    private InitialEndHubName initialEndHubName;

    public static HubTransit create(CreateHubTransitDto dto) {
        return HubTransit.builder()
            .currentHubId(dto.startHubId())
            .currentHubName(new CurrentHubName(dto.startHubName()))
            .nextHubId(dto.endHubId())
            .nextHubName(new NextHubName(dto.endHubName()))
            .deliveryId(dto.deliveryId())
            .deliveryManagerId(dto.deliveryManagerId())
            .initialStartHubName(new InitialStartHubName(dto.startHubName()))
            .initialEndHubName(new InitialEndHubName(dto.endHubName()))
            .build();
    }

    public static HubTransit createNext(
        HubTransit currentTransit,
        UUID newCurrentHubId,
        String newCurrentHubName,
        UUID newNextHubId,
        String newNextHubName,
        UUID newDeliveryManagerId) {
        return HubTransit.builder()
            .currentHubId(newCurrentHubId)
            .currentHubName(new CurrentHubName(newCurrentHubName))
            .deliveryId(currentTransit.getDeliveryId())
            .deliveryManagerId(newDeliveryManagerId)
            .initialStartHubName(currentTransit.getInitialStartHubName())
            .initialEndHubName(currentTransit.getInitialEndHubName())
            .nextHubId(newNextHubId)
            .nextHubName(new NextHubName(newNextHubName))
            .build();
    }

    public void updateDeliveryManagerId(UUID newDeliveryManagerId) {
        this.deliveryManagerId = newDeliveryManagerId;
    }

    public void deleteSoftly() {
        super.deleteSoftly();
    }

    public String getNextDestinationType() {
        return "END_OF_HUB".equals(this.nextHubName.getValue()) ? "COMPANY" : "HUB";
    }

}
