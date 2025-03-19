package on.logistics.authservice.infrastructure.security.jwt;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.Builder;
import on.logistics.authservice.infrastructure.security.cookie.CookieUtil;
import on.logistics.authservice.infrastructure.security.details.AuthDetails;
import on.logistics.authservice.infrastructure.security.details.AuthDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CookieUtil cookieUtil;
    private final AuthDetailsService authDetailsService;

    @Builder
    public JwtAuthorizationFilter(
        JwtUtil jwtUtil,
        CookieUtil cookieUtil,
        AuthDetailsService authDetailsService) {

        this.jwtUtil = jwtUtil;
        this.cookieUtil = cookieUtil;
        this.authDetailsService = authDetailsService;
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {

        String token = jwtUtil.getAccessTokenFromHeader(request);

        if (StringUtils.hasText(token)) {
            jwtUtil.validateToken(token);

            Claims infoClaims = jwtUtil.getUserInfoFromToken(token);

            try {
                setAuthentication(infoClaims.getSubject());
            } catch (Exception e) {
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
