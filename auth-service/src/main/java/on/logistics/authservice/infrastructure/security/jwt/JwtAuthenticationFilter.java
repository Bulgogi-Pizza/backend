package on.logistics.authservice.infrastructure.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import on.logistics.authservice.application.PassportService;
import on.logistics.authservice.enums.AuthRole;
import on.logistics.authservice.exception.AuthException;
import on.logistics.authservice.exception.AuthExceptionCode;
import on.logistics.authservice.global.presentation.dtos.CommonResponse;
import on.logistics.authservice.infrastructure.security.cookie.CookieUtil;
import on.logistics.authservice.infrastructure.security.details.AuthDetailsImpl;
import on.logistics.authservice.presentation.dtos.AuthLoginRequest;
import on.logistics.authservice.presentation.dtos.AuthLoginResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j(topic = "JwtAuthenticationFilter")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtil jwtUtil;
    private final CookieUtil cookieUtil;
    private final PassportService passportService;

    @Builder
    public JwtAuthenticationFilter(
        AuthenticationManager authenticationManager,
        JwtUtil jwtUtil,
        CookieUtil cookieUtil,
        PassportService passportService
    ) {
        setAuthenticationManager(authenticationManager);
        setFilterProcessesUrl("/api/v1/auth/login");
        this.jwtUtil = jwtUtil;
        this.cookieUtil = cookieUtil;
        this.passportService = passportService;
    }

    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest request,
        HttpServletResponse response
    ) {
        try {
            log.info("attemptAuthentication, {}", request.getRequestURI());

            AuthLoginRequest loginRequest = new ObjectMapper()
                .readValue(
                    request.getInputStream(),
                    AuthLoginRequest.class
                );

            return getAuthenticationManager()
                .authenticate(
                    new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password(),
                        null
                    )
                );
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (AuthenticationException e) {
            throw new AuthException(AuthExceptionCode.AUTH_IS_NOT_FOUND);
        }
    }

    @Override
    protected void successfulAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain,
        Authentication auth
    ) throws IOException {
        log.info("successfulAuthentication, {}", auth.getName());

        AuthDetailsImpl authDetails = (AuthDetailsImpl) auth.getPrincipal();
        String username = authDetails.getUsername();
        AuthRole authRole = authDetails.getRole();

        String accessToken = jwtUtil.generateAccessToken(username, authRole);
        String refreshToken = jwtUtil.generateRefreshToken(username, authRole);

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, accessToken);
        cookieUtil.addRefreshTokenToCookie(response, jwtUtil.getTokenWithoutBearer(refreshToken));

        passportService.createAndStorePassport(jwtUtil.getTokenWithoutBearer(refreshToken),
            authDetails.getUserId());

        AuthLoginResponse loginResponse = new AuthLoginResponse(username);
        CommonResponse<AuthLoginResponse> commonResponse = CommonResponse.success(loginResponse);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");

        new ObjectMapper().writeValue(response.getWriter(), commonResponse);
    }

    @Override
    protected void unsuccessfulAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException failed
    ) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");

        CommonResponse<?> errorResponse =
            CommonResponse.exception("아이디 또는 비밀번호가 일치하지 않습니다.");

        new ObjectMapper().writeValue(response.getWriter(), errorResponse);
    }

}
