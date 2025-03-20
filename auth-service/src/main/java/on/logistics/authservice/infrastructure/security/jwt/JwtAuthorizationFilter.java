package on.logistics.authservice.infrastructure.security.jwt;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import on.logistics.authservice.application.PassportService;
import on.logistics.authservice.infrastructure.security.cookie.CookieUtil;
import on.logistics.authservice.infrastructure.security.details.AuthDetails;
import on.logistics.authservice.infrastructure.security.details.AuthDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j(topic = "JwtAuthorizationFilter")
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CookieUtil cookieUtil;
    private final AuthDetailsService authDetailsService;
    private final PassportService passportService;

    @Builder
    public JwtAuthorizationFilter(
        JwtUtil jwtUtil,
        CookieUtil cookieUtil,
        AuthDetailsService authDetailsService,
        PassportService passportService) {

        this.jwtUtil = jwtUtil;
        this.cookieUtil = cookieUtil;
        this.authDetailsService = authDetailsService;
        this.passportService = passportService;
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        log.info("doFilterInternal");
        String token = jwtUtil.getAccessTokenFromHeader(request);

        log.info("token: {}", token);
        if (StringUtils.hasText(token)) {
            jwtUtil.validateToken(token);

            Claims infoClaims = jwtUtil.getUserInfoFromToken(token);

            try {
                setAuthentication(infoClaims.getSubject());
            } catch (Exception e) {
                log.error("setAuthentication Failed, {}",e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

        }

        filterChain.doFilter(request, response);
    }

    public void setAuthentication(String username) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = createAuthentication(username);
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

    private Authentication createAuthentication(String username) {
        AuthDetails authDetails = authDetailsService.loadAuthByUsername(username);

        return new UsernamePasswordAuthenticationToken(
            authDetails,
            null,
            authDetails.getAuthorities());
    }
}
