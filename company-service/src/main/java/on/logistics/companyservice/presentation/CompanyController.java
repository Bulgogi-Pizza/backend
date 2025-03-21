package on.logistics.companyservice.presentation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.companyservice.application.dtos.request.CreateCompanyRequestDto;
import on.logistics.companyservice.application.dtos.request.SearchCompanyRequestDto;
import on.logistics.companyservice.application.dtos.request.UpdateCompanyHubRequestDto;
import on.logistics.companyservice.application.dtos.request.UpdateCompanyRequestDto;
import on.logistics.companyservice.application.dtos.request.UpdateCompanyTypeRequestDto;
import on.logistics.companyservice.application.dtos.request.UpdateCompanyUserRequestDto;
import on.logistics.companyservice.application.service.CompanyService;
import on.logistics.companyservice.domain.entity.enums.CompanyStatus;
import on.logistics.companyservice.domain.entity.enums.CompanyType;
import on.logistics.companyservice.global.application.dtos.PageDto;
import on.logistics.companyservice.global.presentation.dtos.CommonResponse;
import on.logistics.companyservice.presentation.dtos.request.CreateCompanyRequest;
import on.logistics.companyservice.presentation.dtos.request.UpdateCompanyHubRequest;
import on.logistics.companyservice.presentation.dtos.request.UpdateCompanyRequest;
import on.logistics.companyservice.presentation.dtos.request.UpdateCompanyTypeRequest;
import on.logistics.companyservice.presentation.dtos.request.UpdateCompanyUserRequest;
import on.logistics.companyservice.presentation.dtos.response.CreateCompanyResponse;
import on.logistics.companyservice.presentation.dtos.response.GetCompanyResponse;
import on.logistics.companyservice.presentation.dtos.response.SearchCompanyResponse;
import on.logistics.companyservice.presentation.dtos.response.UpdateCompanyHubResponse;
import on.logistics.companyservice.presentation.dtos.response.UpdateCompanyResponse;
import on.logistics.companyservice.presentation.dtos.response.UpdateCompanyTypeResponse;
import on.logistics.companyservice.presentation.dtos.response.UpdateCompanyUserResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/company")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CommonResponse<CreateCompanyResponse>> createCompany(
        @Valid @RequestBody CreateCompanyRequest createCompanyRequest,
        HttpServletRequest passportRequest) {
        final CreateCompanyRequestDto requestDto = CreateCompanyRequestDto.from(
            createCompanyRequest, passportRequest);
        CreateCompanyResponse response = companyService.createCompany(requestDto);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    @GetMapping("/search")
    public ResponseEntity<CommonResponse<PageDto<SearchCompanyResponse>>> searchCompany(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) CompanyType type,
        @RequestParam(required = false) CompanyStatus status, @PageableDefault Pageable pageable) {
        final SearchCompanyRequestDto requestDto = SearchCompanyRequestDto.from(name, type, status,
            pageable);
        PageDto<SearchCompanyResponse> response = companyService.searchCompany(requestDto);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<GetCompanyResponse>> getCompany(@PathVariable UUID id,
        HttpServletRequest passportRequest) {
        GetCompanyResponse response = companyService.getCompany(id);
        return ResponseEntity.ok(CommonResponse.success(response));
    }


    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<UpdateCompanyResponse>> updateCompany(
        @PathVariable UUID id, @Valid @RequestBody UpdateCompanyRequest updateCompanyRequest,
        HttpServletRequest passportRequest) {
        final UpdateCompanyRequestDto requestDto = UpdateCompanyRequestDto.from(id,
            updateCompanyRequest, passportRequest);
        UpdateCompanyResponse response = companyService.updateCompany(requestDto);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<Void>> deleteCompany(@PathVariable UUID id,
        HttpServletRequest passportRequest) {
        companyService.deleteCompany(id, passportRequest);
        return ResponseEntity.ok(CommonResponse.success());
    }

    @PatchMapping("/hub/{id}")
    public ResponseEntity<CommonResponse<UpdateCompanyHubResponse>> updateCompanyHub(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateCompanyHubRequest updateCompanyHubRequest,
        HttpServletRequest passportRequest) {
        final UpdateCompanyHubRequestDto requestDto = UpdateCompanyHubRequestDto.from(
            updateCompanyHubRequest, passportRequest);
        UpdateCompanyHubResponse response = companyService.updateCompanyHub(id, requestDto);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    @PatchMapping("/type/{id}")
    public ResponseEntity<CommonResponse<UpdateCompanyTypeResponse>> updateCompanyType(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateCompanyTypeRequest updateCompanyTypeRequest) {
        final UpdateCompanyTypeRequestDto requestDto = UpdateCompanyTypeRequestDto.from(id,
            updateCompanyTypeRequest.companyType());
        UpdateCompanyTypeResponse response = companyService.updateCompanyType(requestDto);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<CommonResponse<UpdateCompanyUserResponse>> updateCompanyUser(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateCompanyUserRequest updateCompanyUserRequest) {
        final UpdateCompanyUserRequestDto requestDto = UpdateCompanyUserRequestDto.of(id,
            updateCompanyUserRequest.userId());
        UpdateCompanyUserResponse response = companyService.updateCompanyUser(requestDto);
        return ResponseEntity.ok(CommonResponse.success(response));
    }
}
