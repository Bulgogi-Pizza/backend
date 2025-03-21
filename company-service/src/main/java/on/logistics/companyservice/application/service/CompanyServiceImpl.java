package on.logistics.companyservice.application.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.companyservice.application.dtos.request.CreateCompanyRequestDto;
import on.logistics.companyservice.application.dtos.request.SearchCompanyRequestDto;
import on.logistics.companyservice.application.dtos.request.UpdateCompanyHubRequestDto;
import on.logistics.companyservice.application.dtos.request.UpdateCompanyRequestDto;
import on.logistics.companyservice.application.dtos.request.UpdateCompanyTypeRequestDto;
import on.logistics.companyservice.application.dtos.request.UpdateCompanyUserRequestDto;
import on.logistics.companyservice.domain.entity.Company;
import on.logistics.companyservice.domain.entity.dtos.CreateCompanyDto;
import on.logistics.companyservice.domain.repository.CompanyRepository;
import on.logistics.companyservice.exception.CompanyException;
import on.logistics.companyservice.exception.CompanyExceptionCode;
import on.logistics.companyservice.global.application.dtos.PageDto;
import on.logistics.companyservice.global.domain.Passport;
import on.logistics.companyservice.global.enums.AuthRole;
import on.logistics.companyservice.global.utils.PassportUtil;
import on.logistics.companyservice.infrastructure.clients.hub.HubServiceClient;
import on.logistics.companyservice.infrastructure.clients.hub.feign.dtos.GetHubInfo;
import on.logistics.companyservice.presentation.dtos.response.CreateCompanyResponse;
import on.logistics.companyservice.presentation.dtos.response.GetCompanyResponse;
import on.logistics.companyservice.presentation.dtos.response.SearchCompanyResponse;
import on.logistics.companyservice.presentation.dtos.response.UpdateCompanyHubResponse;
import on.logistics.companyservice.presentation.dtos.response.UpdateCompanyResponse;
import on.logistics.companyservice.presentation.dtos.response.UpdateCompanyTypeResponse;
import on.logistics.companyservice.presentation.dtos.response.UpdateCompanyUserResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final HubServiceClient hubServiceClient;
    private final PassportUtil passportUtil;

    @Override
    @Transactional
    public CreateCompanyResponse createCompany(CreateCompanyRequestDto requestDto) {
        // todo : 임시 유저 아이디 발급. 패스포트로 받아서 넣기
        UUID userId = UUID.randomUUID();
        Passport passport = passportUtil.getPassportByHttpServletRequest(
            requestDto.passportRequest());

        if (!passport.getRole().equals(AuthRole.MASTER.name()) && !passport.getRole()
            .equals(AuthRole.HUB_MANAGER.name())) {
            throw new CompanyException(CompanyExceptionCode.COMPANY_ACCESS_DENIED);
        }

        companyRepository.findByUserId(userId).ifPresent(company -> {
            throw new CompanyException(CompanyExceptionCode.COMPANY_USER_ID_DUPLICATE);
        });

        CreateCompanyDto createCompanyDto = CreateCompanyDto.from(userId, requestDto.companyName(),
            requestDto.companyType(), requestDto.companyAddress(), requestDto.managedHubId());
        Company company = Company.create(createCompanyDto);
        Company saved = companyRepository.save(company);
        return CreateCompanyResponse.of(saved.getId());
    }

    @Override
    public PageDto<SearchCompanyResponse> searchCompany(SearchCompanyRequestDto requestDto) {
        Page<Company> companyPage = companyRepository.searchCompany(requestDto);
        Page<SearchCompanyResponse> responsePage = companyPage.map(SearchCompanyResponse::from);
        return PageDto.from(responsePage);
    }

    @Override
    public GetCompanyResponse getCompany(UUID id) {
        Company company = getOrElseThrow(id);
        return GetCompanyResponse.of(company.getId(), company.getName().getValue(),
            company.getType(), company.getStatus(), company.getManagedHubId(),
            company.getAddress().getValue());
    }

    @Override
    @Transactional
    public UpdateCompanyResponse updateCompany(UUID id, UpdateCompanyRequestDto requestDto) {
        // todo : 유저의 아이디 정보를 받아와서 본인 회사인지 체크하는 로직 필요
        Company company = getOrElseThrow(id);
        company.update(requestDto.companyName(), requestDto.companyAddress());
        return UpdateCompanyResponse.of(company.getId());
    }

    @Override
    @Transactional
    public void deleteCompany(UUID id) {
        // todo : 유저의 아이디 정보를 받아와서 본인 회사인지 체크하는 로직 필요
        Company company = getOrElseThrow(id);
        companyRepository.delete(company);
    }

    @Override
    @Transactional
    public UpdateCompanyHubResponse updateCompanyHub(UUID id,
        UpdateCompanyHubRequestDto requestDto) {
        // todo : 허브 매니저 및 마스터만 이용 가능
        Company company = getOrElseThrow(id);
        GetHubInfo hubInfo = hubServiceClient.getHubInfo(requestDto.managedHubId());
        if (hubInfo == null) {
            throw new CompanyException(CompanyExceptionCode.COMPANY_HUB_NOT_FOUND);
        }
        company.updateHub(requestDto.managedHubId());
        return UpdateCompanyHubResponse.of(company.getId());
    }

    @Override
    @Transactional
    public UpdateCompanyTypeResponse updateCompanyType(UpdateCompanyTypeRequestDto requestDto) {
        // todo : 유저의 아이디 정보를 받아와서 본인 회사인지 체크하는 로직 필요
        Company company = getOrElseThrow(requestDto.companyId());
        company.updateCompanyType(requestDto.companyType());
        return UpdateCompanyTypeResponse.of(company.getId());
    }

    @Override
    @Transactional
    public UpdateCompanyUserResponse updateCompanyUser(UpdateCompanyUserRequestDto requestDto) {
        Company company = getOrElseThrow(requestDto.companyId());
        company.updateCompanyUser(requestDto.userId());
        return UpdateCompanyUserResponse.of(company.getId());
    }

    private Company getOrElseThrow(UUID id) {
        return companyRepository.findById(id)
            .orElseThrow(() -> new CompanyException(CompanyExceptionCode.COMPANY_IS_NOT_FOUND));
    }
}