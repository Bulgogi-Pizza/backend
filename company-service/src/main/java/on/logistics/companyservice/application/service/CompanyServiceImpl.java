package on.logistics.companyservice.application.service;

import jakarta.servlet.http.HttpServletRequest;
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
        Passport passport = passportUtil.getPassportByHttpServletRequest(
            requestDto.passportRequest());

        if (!passport.getRole().equals(AuthRole.MASTER.name()) && !passport.getRole()
            .equals(AuthRole.HUB_MANAGER.name())) {
            throw new CompanyException(CompanyExceptionCode.COMPANY_ACCESS_DENIED);
        }

        companyRepository.findByUserId(passport.getUserId()).ifPresent(company -> {
            throw new CompanyException(CompanyExceptionCode.COMPANY_USER_ID_DUPLICATE);
        });

        GetHubInfo hubInfo = hubServiceClient.getHubInfo(requestDto.managedHubId());
        if (hubInfo == null) {
            throw new CompanyException(CompanyExceptionCode.COMPANY_HUB_NOT_FOUND);
        }

        CreateCompanyDto createCompanyDto = CreateCompanyDto.from(passport.getUserId(),
            requestDto.companyName(), requestDto.companyType(), requestDto.companyAddress(),
            requestDto.managedHubId());
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
    public UpdateCompanyResponse updateCompany(UpdateCompanyRequestDto requestDto) {
        Passport passport = passportUtil.getPassportByHttpServletRequest(
            requestDto.passportRequest());

        if (passport.getRole().equals(AuthRole.DELIVERY_MANAGER.name())) {
            throw new CompanyException(CompanyExceptionCode.COMPANY_ACCESS_DENIED);
        }

        Company company = getOrElseThrow(requestDto.companyId());
        // todo : 허브 매니저 검증 로직 추가
        if (!company.getId().equals(passport.getUserId())) {
            throw new CompanyException(CompanyExceptionCode.COMPANY_ACCESS_DENIED);
        }

        company.update(requestDto.companyName(), requestDto.companyAddress());
        return UpdateCompanyResponse.of(company.getId());
    }

    @Override
    @Transactional
    public void deleteCompany(UUID id, HttpServletRequest passportRequest) {
        Passport passport = passportUtil.getPassportByHttpServletRequest(
            passportRequest);

        if (!passport.getRole().equals(AuthRole.MASTER.name()) && !passport.getRole()
            .equals(AuthRole.HUB_MANAGER.name())) {
            throw new CompanyException(CompanyExceptionCode.COMPANY_ACCESS_DENIED);
        }

        Company company = getOrElseThrow(id);

        // todo : 권한 체크 로직 필요
        companyRepository.delete(company);
    }

    @Override
    @Transactional
    public UpdateCompanyHubResponse updateCompanyHub(UUID id,
        UpdateCompanyHubRequestDto requestDto) {

        Passport passport = passportUtil.getPassportByHttpServletRequest(
            requestDto.passportRequest());

        if (!passport.getRole().equals(AuthRole.MASTER.name()) && !passport.getRole()
            .equals(AuthRole.HUB_MANAGER.name())) {
            throw new CompanyException(CompanyExceptionCode.COMPANY_ACCESS_DENIED);
        }

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