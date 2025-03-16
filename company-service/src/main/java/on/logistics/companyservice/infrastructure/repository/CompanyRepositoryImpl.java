package on.logistics.companyservice.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.companyservice.domain.entity.Company;
import on.logistics.companyservice.domain.repository.CompanyRepository;
import on.logistics.companyservice.infrastructure.jpa.CompanyJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CompanyRepositoryImpl implements CompanyRepository {

    private final CompanyJpaRepository companyJpaRepository;

    @Override
    public Company save(Company company) {
        return companyJpaRepository.save(company);
    }

    @Override
    public Optional<Company> findByUserId(UUID userId) {
        return companyJpaRepository.findByUserId(userId);
    }

    @Override
    public Optional<Company> findByIdAndIsDeleted(UUID id, boolean isDeleted) {
        return companyJpaRepository.findByIdAndIsDeleted(id, isDeleted);
    }
}
