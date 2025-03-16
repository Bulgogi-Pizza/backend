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
import on.logistics.companyservice.application.dtos.request.CreateCompanyRequestDto;
import on.logistics.companyservice.application.dtos.request.UpdateCompanyRequestDto;
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

    @Column(name = "user_id", unique = true, nullable = false)
    private UUID userId;

    @Embedded
    private Name name;

    @Enumerated(EnumType.STRING)
    private CompanyType type;

    @Column(name = "managed_hub_id", nullable = true)
    private UUID managedHubId;

    @Enumerated(EnumType.STRING)
    private Address address;

    private Company(UUID userId, Name name, CompanyType type, Address address) {
        this.userId = userId;
        this.name = name;
        this.type = type;
        this.address = address;
    }

    public static Company create(UUID userId, CreateCompanyRequestDto requestDto) {
        return new Company(
            userId,
            new Name(requestDto.companyName()),
            requestDto.companyType(),
            new Address(requestDto.companyAddress())
        );
    }

    public void update(UpdateCompanyRequestDto newCompany) {
        this.name = name.update(newCompany.companyName());
        this.address = address.update(newCompany.companyAddress());
    }

    public void delete() {
        deleteSoftly();
    }

    public void updateHub(UUID managedHubId) {
        this.managedHubId = managedHubId;
    }
}
