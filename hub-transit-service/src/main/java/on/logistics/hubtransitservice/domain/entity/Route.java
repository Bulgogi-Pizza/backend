package on.logistics.hubtransitservice.domain.entity;

import jakarta.persistence.Column;
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
import on.logistics.hubtransitservice.global.domain.BaseEntity;

@Entity
@Table(name = "p_route")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Route extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID routeId;

    @Column(name = "start_hub_name", nullable = false)
    private String startHubName;

    @Column(name = "end_hub_name", nullable = false)
    private String endHubName;

    @Column(name = "path_json", nullable = false, columnDefinition = "jsonb")
    private String pathJson;


}
