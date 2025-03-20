package on.logistics.hubservice.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "p_center_spoke_hub_link")
public class CenterSpokeHubLink extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id")
    private Hub center;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spoke_id")
    private Hub spoke;

    @Builder
    private CenterSpokeHubLink(Hub center, Hub spoke) {
        this.center = center;
        this.spoke = spoke;
    }
}
