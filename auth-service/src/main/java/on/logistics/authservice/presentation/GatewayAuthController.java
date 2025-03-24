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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/gateway/v1/auth")
public class GatewayAuthController {

    private final AuthServiceImpl authService;

    @GetMapping("/validate")
    public ResponseEntity<CommonResponse<AuthValidateResponse>> validate(
        HttpServletRequest request
    ) {
        log.info("validate request: {}", request);

        AuthValidateResponse response = authService.validate(request);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

}
