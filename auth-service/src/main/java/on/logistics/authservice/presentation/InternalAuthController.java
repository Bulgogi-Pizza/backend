package on.logistics.authservice.presentation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.authservice.application.AuthServiceImpl;
import on.logistics.authservice.global.presentation.dtos.CommonResponse;
import on.logistics.authservice.presentation.dtos.AuthReissueTokensResponse;
import on.logistics.authservice.presentation.dtos.AuthValidateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/internal/v1/auth")
public class InternalAuthController {

    private final AuthServiceImpl authService;

    @PostMapping("/refresh")
    public ResponseEntity<CommonResponse<AuthReissueTokensResponse>> reissueRefreshToken(
        HttpServletRequest servletRequest,
        HttpServletResponse servletResponse
    ) {
        log.info("reissue refresh token request: {}", servletRequest);

        AuthReissueTokensResponse response = authService.reIssueTokens(servletRequest,
            servletResponse);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    @PutMapping("/my")
    public ResponseEntity<CommonResponse<Void>> deleteAuthByPassport(
        HttpServletRequest request
    ) {
        log.info("delete auth by request: {}", request);

        authService.deleteAuthByPassportId(request);
        return ResponseEntity.ok(CommonResponse.success());
    }

}
