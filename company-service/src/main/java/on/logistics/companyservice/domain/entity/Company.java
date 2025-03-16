package on.logistics.companyservice.domain.entity;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import on.logistics.companyservice.domain.entity.enums.CompanyType;
import on.logistics.companyservice.domain.entity.vo.Address;
import on.logistics.companyservice.domain.entity.vo.Name;
import on.logistics.companyservice.global.domain.BaseEntity;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    @Embedded
    private Name name;

    @Enumerated(EnumType.STRING)
    private CompanyType type;

    @Column(name = "managed_hub_id")
    private UUID managedHubId;

    @Enumerated(EnumType.STRING)
    private Address address;
}
