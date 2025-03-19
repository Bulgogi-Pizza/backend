package on.logistics.authservice.presentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.authservice.application.AuthServiceImpl;
import on.logistics.authservice.application.dtos.AuthSignupRequestDto;
import on.logistics.authservice.presentation.dtos.AuthSignupRequest;
import on.logistics.authservice.presentation.dtos.AuthSignupResponse;
import on.logistics.authservice.global.presentation.dtos.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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

        final var requestDto = AuthSignupRequestDto.from(request);
        final var response = authService.signup(requestDto);
        return ResponseEntity.ok(CommonResponse.success(response));
    }
}
