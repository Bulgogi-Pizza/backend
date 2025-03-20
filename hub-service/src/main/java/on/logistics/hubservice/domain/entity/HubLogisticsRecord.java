package on.logistics.hubservice.domain.entity;

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
import on.logistics.hubservice.global.domain.BaseEntity;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "p_hub_logistics_record")
public class HubLogisticsRecord extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private LogisticsStatus status;

    @Column(nullable = false)
    private UUID hubId;

    @Column(nullable = false)
    private UUID deliveryId;

    @Builder
    private HubLogisticsRecord(LogisticsStatus status, UUID hubId, UUID deliveryId) {
        this.status = status;
        this.hubId = hubId;
        this.deliveryId = deliveryId;
    }

    public static HubLogisticsRecord storage(UUID hubId, UUID deliveryId) {
        return HubLogisticsRecord.builder()
            .status(LogisticsStatus.STORAGE)
            .hubId(hubId)
            .deliveryId(deliveryId)
            .build();
    }

    public void retrieval() {
        this.status = LogisticsStatus.RETRIEVAL;
    }
}
