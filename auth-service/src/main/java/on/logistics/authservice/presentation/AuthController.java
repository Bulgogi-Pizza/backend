package on.logistics.authservice.presentation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.authservice.application.AuthServiceImpl;
import on.logistics.authservice.application.dtos.AuthSignupRequestDto;
import on.logistics.authservice.global.presentation.dtos.CommonResponse;
import on.logistics.authservice.presentation.dtos.AuthReissueTokensResponse;
import on.logistics.authservice.presentation.dtos.AuthSignupRequest;
import on.logistics.authservice.presentation.dtos.AuthSignupResponse;
import on.logistics.authservice.presentation.dtos.AuthValidateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthServiceImpl authService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponse<AuthSignupResponse>> userSignup(
        @RequestBody AuthSignupRequest request
    ) {
        log.info("user signup request: {}", request);

        AuthSignupRequestDto requestDto = AuthSignupRequestDto.from(request);
        AuthSignupResponse response = authService.signup(requestDto);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    @PostMapping("/logout")
    public ResponseEntity<CommonResponse<Void>> logout(
        HttpServletRequest request
    ) {
        log.info("logout request: {}", request);

        authService.logout(request);
        return ResponseEntity.ok(CommonResponse.success());
    }


    @DeleteMapping("/my")
    public ResponseEntity<CommonResponse<Void>> deleteAuthByPassport(
        HttpServletRequest request
    ) {
        log.info("delete auth by request: {}", request);

        authService.deleteAuthByPassportId(request);
        return ResponseEntity.ok(CommonResponse.success());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<CommonResponse<Void>> deleteAuthByUserId(
        @PathVariable String userId,
        HttpServletRequest request
    ) {
        log.info("delete auth by Id: {}", userId);

        authService.deleteAuthByUserId(userId, request);
        return ResponseEntity.ok(CommonResponse.success());
    }

    @DeleteMapping("/withdraw/my")
    public ResponseEntity<CommonResponse<Void>> withdrawUserByPassport(
        HttpServletRequest request
    ) {
        log.info("withdraw user by request: {}", request);

        authService.withdrawUserByPassport(request);
        return ResponseEntity.ok(CommonResponse.success());
    }

    @DeleteMapping("/withdraw/{userId}")
    public ResponseEntity<CommonResponse<Void>> withdrawUserByUserId(
        @PathVariable String userId,
        HttpServletRequest request
    ) {
        log.info("withdraw user by Id: {}", userId);

        authService.withdrawUserByUserId(userId, request);
        return ResponseEntity.ok(CommonResponse.success());
    }

}
