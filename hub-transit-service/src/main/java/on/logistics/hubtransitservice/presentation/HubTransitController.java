package on.logistics.hubtransitservice.presentation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.hubtransitservice.application.HubTransitService;
import on.logistics.hubtransitservice.application.dtos.request.GetNextHubRequestDto;
import on.logistics.hubtransitservice.application.dtos.request.UpdateHubTransitRequestDto;
import on.logistics.hubtransitservice.exception.HubTransitException;
import on.logistics.hubtransitservice.exception.HubTransitExceptionCode;
import on.logistics.hubtransitservice.global.presentation.dtos.CommonResponse;
import on.logistics.hubtransitservice.global.presentation.dtos.PageDto;
import on.logistics.hubtransitservice.global.utils.PassportUtil;
import on.logistics.hubtransitservice.presentation.dtos.request.CreateHubTransitRequest;
import on.logistics.hubtransitservice.presentation.dtos.request.InboundHubTransitRequest;
import on.logistics.hubtransitservice.presentation.dtos.request.UpdateHubTransitRequest;
import on.logistics.hubtransitservice.presentation.dtos.response.CreateHubTransitResponse;
import on.logistics.hubtransitservice.presentation.dtos.response.GetHubTransitResponse;
import on.logistics.hubtransitservice.presentation.dtos.response.GetNextHubResponse;
import on.logistics.hubtransitservice.presentation.dtos.response.SearchHubTransitResponse;
import on.logistics.hubtransitservice.presentation.dtos.response.UpdateHubTransitResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hub-transit")
public class HubTransitController {

    private final HubTransitService hubTransitService;
    private final PassportUtil passportUtil;

    @PostMapping("/route")
    public ResponseEntity<CommonResponse<CreateHubTransitResponse>> createHubTransit(
        @RequestBody @Valid final CreateHubTransitRequest request,
        HttpServletRequest servletRequest
    ) {
        String role = passportUtil.getPassportByHttpServletRequest(servletRequest).getRole();
        if (!role.equals("MASTER")) {
            throw new HubTransitException(HubTransitExceptionCode.HAS_NO_AUTHORITY);
        }
        final var requestDto = CreateHubTransitRequest.from(request);
        final var responseDto = hubTransitService.createHubTransit(requestDto);
        return ResponseEntity.ok(CommonResponse.success(responseDto));
    }

    @PostMapping("/route/next")
    public ResponseEntity<CommonResponse<Void>> processInboundHubTransit(
        @RequestBody @Valid final InboundHubTransitRequest request,
        HttpServletRequest servletRequest
    ) {
        String role = passportUtil.getPassportByHttpServletRequest(servletRequest).getRole();
        if (!role.equals("MASTER")) {
            throw new HubTransitException(HubTransitExceptionCode.HAS_NO_AUTHORITY);
        }
        final var requestDto = InboundHubTransitRequest.from(request);
        hubTransitService.processInboundHubTransit(requestDto);
        return ResponseEntity.ok(CommonResponse.success());
    }

    @GetMapping("/{transitId}")
    public ResponseEntity<CommonResponse<GetHubTransitResponse>> getHubTransit(
        @PathVariable UUID transitId, HttpServletRequest servletRequest
    ) {
        String role = passportUtil.getPassportByHttpServletRequest(servletRequest).getRole();
        if (!("MASTER".equals(role) || "HUB_MANAGER".equals(role)
            || "DELIVERY_MANAGER".equals(role) || "COMPANY_MANAGER".equals(role))) {
            throw new HubTransitException(HubTransitExceptionCode.HAS_NO_AUTHORITY);
        }
        final var responseDto = hubTransitService.getHubTransit(transitId);
        return ResponseEntity.ok(CommonResponse.success(responseDto));
    }

    @GetMapping("/search")
    public ResponseEntity<CommonResponse<PageDto<SearchHubTransitResponse>>> searchHubTransit(
        @RequestParam(required = false) String keyword, @PageableDefault Pageable pageable,
        HttpServletRequest servletRequest
    ) {
        String role = passportUtil.getPassportByHttpServletRequest(servletRequest).getRole();
        if (!("MASTER".equals(role) || "HUB_MANAGER".equals(role)
            || "DELIVERY_MANAGER".equals(role) || "COMPANY_MANAGER".equals(role))) {
            throw new HubTransitException(HubTransitExceptionCode.HAS_NO_AUTHORITY);
        }
        final var resultPage = hubTransitService.searchHubTransit(keyword, pageable);
        final var pageDto = PageDto.from(resultPage);
        return ResponseEntity.ok(CommonResponse.success(pageDto));
    }

    @GetMapping("/next")
    public ResponseEntity<CommonResponse<GetNextHubResponse>> getNextHubTransit(
        @RequestParam("transitId") UUID transitId, @RequestParam("currentHubId") UUID currentHubId,
        HttpServletRequest servletRequest
    ) {
        String role = passportUtil.getPassportByHttpServletRequest(servletRequest).getRole();
        if (!("MASTER".equals(role) || "HUB_MANAGER".equals(role)
            || "DELIVERY_MANAGER".equals(role) || "COMPANY_MANAGER".equals(role))) {
            throw new HubTransitException(HubTransitExceptionCode.HAS_NO_AUTHORITY);
        }
        final var requestDto = GetNextHubRequestDto.of(transitId, currentHubId);
        final var responseDto = hubTransitService.getNextHubTransit(requestDto);
        return ResponseEntity.ok(CommonResponse.success(responseDto));
    }

    @PatchMapping("/{transitId}")
    public ResponseEntity<CommonResponse<UpdateHubTransitResponse>> updateHubTransit(
        @PathVariable UUID transitId,
        @RequestBody @Valid UpdateHubTransitRequest request,
        HttpServletRequest servletRequest
    ) {
        String role = passportUtil.getPassportByHttpServletRequest(servletRequest).getRole();
        if (!("MASTER".equals(role))) {
            throw new HubTransitException(HubTransitExceptionCode.HAS_NO_AUTHORITY);
        }
        final var requestDto = UpdateHubTransitRequestDto.of(transitId, request);
        final var responseDto = hubTransitService.updateHubTransit(requestDto);
        return ResponseEntity.ok(CommonResponse.success(responseDto));
    }

    @DeleteMapping("/{transitId}")
    public ResponseEntity<CommonResponse<Void>> deleteHubTransit(
        @PathVariable UUID transitId,
        HttpServletRequest servletRequest
    ) {
        String role = passportUtil.getPassportByHttpServletRequest(servletRequest).getRole();
        if (!("MASTER".equals(role))) {
            throw new HubTransitException(HubTransitExceptionCode.HAS_NO_AUTHORITY);
        }
        hubTransitService.deleteHubTransit(transitId);
        return ResponseEntity.ok(CommonResponse.success());
    }

}
