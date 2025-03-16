package on.logistics.companyservice.application.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.companyservice.application.dtos.request.CreateCompanyRequestDto;
import on.logistics.companyservice.domain.entity.Company;
import on.logistics.companyservice.domain.repository.CompanyRepository;
import on.logistics.companyservice.exception.CompanyException;
import on.logistics.companyservice.exception.CompanyExceptionCode;
import on.logistics.companyservice.presentation.dtos.response.CreateCompanyResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Transactional
    public CreateCompanyResponse createCompany(CreateCompanyRequestDto requestDto) {
        // todo : 임시 유저 아이디 발급 로직 수정 필요
        UUID userId = UUID.randomUUID();

        companyRepository.findByUserId(userId).ifPresent(company -> {
            throw new CompanyException(CompanyExceptionCode.COMPANY_USER_ID_DUPLICATE);
        });

        Company company = Company.create(userId, requestDto);
        Company saved = companyRepository.save(company);
        return CreateCompanyResponse.of(saved);
    }
}