package on.logistics.authservice.application;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.authservice.application.dtos.AuthSignupRequestDto;
import on.logistics.authservice.application.dtos.AuthSignupResponseDto;
import on.logistics.authservice.domain.entity.Auth;
import on.logistics.authservice.domain.repository.AuthRepository;
import on.logistics.authservice.exception.AuthException;
import on.logistics.authservice.exception.AuthExceptionCode;
import on.logistics.authservice.infrastructure.feign.UserClientService;
import on.logistics.authservice.infrastructure.feign.dtos.UserCreateRequest;
import on.logistics.authservice.infrastructure.feign.dtos.UserCreateResponse;
import on.logistics.authservice.infrastructure.security.cookie.CookieUtil;
import on.logistics.authservice.infrastructure.security.jwt.JwtUtil;
import on.logistics.authservice.presentation.dtos.AuthSignupResponse;
import on.logistics.authservice.presentation.dtos.AuthValidateResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "AuthServiceImpl")
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserClientService userClientService;
    private final CookieUtil cookieUtil;
    private final PassportService passportService;

    @Transactional
    public AuthSignupResponse signup(AuthSignupRequestDto authRequestDto) {

        if (authRepository.existsByUsername(authRequestDto.username())) {
            throw new AuthException(AuthExceptionCode.AUTH_USERNAME_DUPLICATE);
        }

        UserCreateRequest userRequest = UserCreateRequest.from(authRequestDto);
        UserCreateResponse userResponse = userClientService.createUser(userRequest);

        String encodedPassword = passwordEncoder.encode(authRequestDto.password().toString());
        Auth auth = Auth.from(authRequestDto, userResponse, encodedPassword);

        // TODO: createdBy 해결 필
        auth.create();
        Auth savedAuth = authRepository.save(auth);

        AuthSignupResponseDto authResponseDto = AuthSignupResponseDto.from(savedAuth, userResponse);

        return AuthSignupResponse.from(authResponseDto);
    }

    @Override
    public AuthValidateResponse validate(
        HttpServletRequest request
    ) {
        String token = cookieUtil.getRefreshTokenFromCookie(request);
        String passportId = passportService.getPassportIdByToken(token);

        return AuthValidateResponse.of(passportId);
    }


}
