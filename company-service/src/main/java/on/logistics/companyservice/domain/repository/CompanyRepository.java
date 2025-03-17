package on.logistics.companyservice.domain.repository;

import java.util.Optional;
import java.util.UUID;
import on.logistics.companyservice.domain.entity.Company;

public interface CompanyRepository {

    Company save(Company company);

    void delete(Company company);

    Optional<Company> findById(UUID id);

    Optional<Company> findByUserId(UUID userId);
}
