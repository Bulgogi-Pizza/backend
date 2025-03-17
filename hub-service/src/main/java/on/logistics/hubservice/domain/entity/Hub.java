package on.logistics.hubservice.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import on.logistics.hubservice.application.dtos.request.CreateHubRequestDto;
import on.logistics.hubservice.application.dtos.request.UpdateHubRequestDto;
import on.logistics.hubservice.domain.entity.vo.Address;
import on.logistics.hubservice.domain.entity.vo.Name;
import on.logistics.hubservice.global.domain.BaseEntity;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "p_hub")
public class Hub extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Embedded
    private Name name;

    @Enumerated(EnumType.STRING)
    private HubType type;

    @Embedded
    private Address address;

    @Column(nullable = false, precision = 11, scale = 9)
    private BigDecimal latitude;

    @Column(nullable = false, precision = 12, scale = 8)
    private BigDecimal longitude;

    @Builder
    private Hub(Name name, HubType type, Address address, BigDecimal latitude,
        BigDecimal longitude) {
        this.name = name;
        this.type = type;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Hub create(CreateHubRequestDto dto, BigDecimal latitude, BigDecimal longitude) {
        return Hub.builder()
            .name(new Name(dto.hubName()))
            .type(dto.hubType())
            .address(new Address(dto.hubAddress()))
            .latitude(latitude)
            .longitude(longitude)
            .build();
    }

    public void update(UpdateHubRequestDto dto) {
        this.name = new Name(dto.hubName());
        this.type = dto.hubType();
        this.address = new Address(dto.hubAddress());
    }
}
