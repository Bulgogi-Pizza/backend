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
import on.logistics.companyservice.infrastructure.clients.hub.feign.dtos.GetHubManagerBooleanResponse;
import on.logistics.companyservice.infrastructure.clients.hub.feign.dtos.HubManagerBooleanRequest;
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
        Passport passport = getPassport(requestDto.passportRequest());
        notMasterAndNotHubManagerValid(passport);

        companyRepository.findByUserId(passport.getUserId()).ifPresent(company -> {
            throw new CompanyException(CompanyExceptionCode.COMPANY_USER_ID_DUPLICATE);
        });

        GetHubInfo hubInfo = hubServiceClient.getHubInfo(requestDto.managedHubId());
        if (hubInfo == null) {
            throw new CompanyException(CompanyExceptionCode.COMPANY_HUB_NOT_FOUND);
        }

        validHubManagerHub(passport, hubInfo.id());

        CreateCompanyDto createCompanyDto = CreateCompanyDto.from(requestDto.userId(),
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
        return GetCompanyResponse.of(company.getId(), company.getUserId(),
            company.getName().getValue(), company.getType(), company.getStatus(),
            company.getManagedHubId(), company.getAddress().getValue());
    }

    @Override
    @Transactional
    public UpdateCompanyResponse updateCompany(UpdateCompanyRequestDto requestDto) {
        Passport passport = getPassport(requestDto.passportRequest());

        if (passport.getRole().equals(AuthRole.DELIVERY_MANAGER.name())) {
            throw new CompanyException(CompanyExceptionCode.COMPANY_ACCESS_DENIED);
        }

        Company company = getOrElseThrow(requestDto.companyId());

        validHubManagerHubAndCompanyManager(passport, company);

        company.update(requestDto.companyName(), requestDto.companyAddress());
        return UpdateCompanyResponse.of(company.getId());
    }

    @Override
    @Transactional
    public void deleteCompany(UUID id, HttpServletRequest passportRequest) {
        Passport passport = getPassport(passportRequest);

        if (!passport.getRole().equals(AuthRole.MASTER.name()) && !passport.getRole()
            .equals(AuthRole.HUB_MANAGER.name())) {
            throw new CompanyException(CompanyExceptionCode.COMPANY_ACCESS_DENIED);
        }

        Company company = getOrElseThrow(id);

        validHubManagerHub(passport, company.getManagedHubId());
        company.deleteSoftly();
    }

    @Override
    @Transactional
    public UpdateCompanyHubResponse updateCompanyHub(UUID id,
        UpdateCompanyHubRequestDto requestDto) {

        Passport passport = getPassport(requestDto.passportRequest());

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
        Passport passport = getPassport(requestDto.passportRequest());

        if (passport.getRole().equals(AuthRole.DELIVERY_MANAGER.name())) {
            throw new CompanyException(CompanyExceptionCode.COMPANY_ACCESS_DENIED);
        }

        Company company = getOrElseThrow(requestDto.companyId());
        validHubManagerHubAndCompanyManager(passport, company);
        company.updateCompanyType(requestDto.companyType());
        return UpdateCompanyTypeResponse.of(company.getId());
    }

    @Override
    @Transactional
    public UpdateCompanyUserResponse updateCompanyUser(UpdateCompanyUserRequestDto requestDto) {
        Passport passport = getPassport(requestDto.passportRequest());
        notMasterAndNotHubManagerValid(passport);
        Company company = getOrElseThrow(requestDto.companyId());
        validHubManagerHubAndCompanyManager(passport, company);
        company.updateCompanyUser(requestDto.userId());
        return UpdateCompanyUserResponse.of(company.getId());
    }

    private Passport getPassport(HttpServletRequest passportRequest) {
        return passportUtil.getPassportByHttpServletRequest(passportRequest);
    }

    private void notMasterAndNotHubManagerValid(Passport passport) {
        if (passport.getRole().equals(AuthRole.COMPANY_MANAGER.name()) || passport.getRole()
            .equals(AuthRole.DELIVERY_MANAGER.name())) {
            throw new CompanyException(CompanyExceptionCode.COMPANY_ACCESS_DENIED);
        }
    }

    private void validHubManagerHubAndCompanyManager(Passport passport, Company company) {
        validHubManagerHub(passport, company.getManagedHubId());
        if (passport.getRole().equals(AuthRole.COMPANY_MANAGER.name())) {
            if (!company.getUserId().equals(passport.getUserId())) {
                throw new CompanyException(CompanyExceptionCode.COMPANY_ACCESS_DENIED);
            }
        }
    }

    private void validHubManagerHub(Passport passport, UUID company) {
        if (passport.getRole().equals(AuthRole.HUB_MANAGER.name())) {
            HubManagerBooleanRequest hubManagerBooleanRequest = HubManagerBooleanRequest.of(
                passport.getUserId(), company);
            GetHubManagerBooleanResponse hubManagerBoolean = hubServiceClient.getHubManagerBoolean(
                hubManagerBooleanRequest);
            if (Boolean.FALSE.equals(hubManagerBoolean.isExist())) {
                throw new CompanyException(CompanyExceptionCode.COMPANY_ACCESS_DENIED);
            }
        }
    }

    private Company getOrElseThrow(UUID id) {
        return companyRepository.findById(id)
            .orElseThrow(() -> new CompanyException(CompanyExceptionCode.COMPANY_IS_NOT_FOUND));
    }
}