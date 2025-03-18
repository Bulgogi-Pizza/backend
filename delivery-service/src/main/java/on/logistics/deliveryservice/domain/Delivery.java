package on.logistics.deliveryservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
import on.logistics.deliveryservice.application.dtos.DeliveryHubInfoDto;
import on.logistics.deliveryservice.domain.dtos.CreateDeliveryDto;
import on.logistics.deliveryservice.domain.enums.DeliveryStatus;
import on.logistics.deliveryservice.domain.vo.Destination;
import on.logistics.deliveryservice.domain.vo.Recipient;
import on.logistics.deliveryservice.domain.vo.RecipientSlackEmail;
import on.logistics.deliveryservice.global.domain.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SQLRestriction("is_deleted = false")
@SQLDelete(sql = "UPDATE delivery SET is_deleted = true WHERE id = ?")
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "order_id", nullable = false)
    private UUID orderId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @Column(name = "start_hub_id", nullable = false)
    private UUID startHubId;

    @Column(name = "end_hub_id", nullable = false)
    private UUID endHubId;

    @Embedded
    @Column(nullable = false)
    private Destination destination;

    @Embedded
    @Column(nullable = false)
    private Recipient recipient;

    @Embedded
    @Column(nullable = false)
    private RecipientSlackEmail recipientSlackEmail;

    @Column(name = "company_delivery_managed_id")
    private UUID companyDeliveryManagerId;

    public static Delivery create(CreateDeliveryDto entityRequestDto) {
        return builder().orderId(entityRequestDto.orderId()).status(DeliveryStatus.HUB_WAITING)
            .startHubId(entityRequestDto.startHubId()).endHubId(entityRequestDto.endHubId())
            .destination(new Destination(entityRequestDto.destination()))
            .recipient(new Recipient(entityRequestDto.recipient()))
            .recipientSlackEmail(new RecipientSlackEmail(entityRequestDto.recipientSlackEmail()))
            .build();
    }

    public void update(String description, DeliveryHubInfoDto hubInfo) {
        if (description != null) {
            this.destination = destination.update(description);
        }
        if (hubInfo != null) {
            this.endHubId = hubInfo.endHubId();
        }
    }

    public void updateAssignManager(UUID companyDeliveryManagerId) {
        this.companyDeliveryManagerId = companyDeliveryManagerId;
    }

    public void updateDeliveryStatusHubMoving() {
        this.status = DeliveryStatus.HUB_MOVING;
    }

    public void updateDeliveryStatusHubArrive() {
        this.status = DeliveryStatus.HUB_ARRIVE;
    }

    public void updateDeliveryStatusCompanyMoving() {
        this.status = DeliveryStatus.COMPANY_MOVING;
    }

    public void updateDeliveryStatusCompanyArrive() {
        this.status = DeliveryStatus.COMPANY_ARRIVE;
    }

    public void updateDeliveryStatusCancel() {
        this.status = DeliveryStatus.CANCEL;
    }
}
