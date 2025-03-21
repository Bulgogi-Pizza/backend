package on.logistics.authservice.infrastructure.security.details;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import on.logistics.authservice.domain.entity.Auth;
import on.logistics.authservice.enums.AuthRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class AuthDetailsImpl implements AuthDetails {

    private final Auth auth;

    public AuthDetailsImpl(Auth auth) {
        this.auth = auth;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        AuthRole authRole = auth.getRole();
        String authority = authRole.name();

        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(grantedAuthority);

        return authorities;
    }

    @Override
    public String getPassword() {
        return auth.getPassword();
    }

    @Override
    public String getUsername() {
        return auth.getUsername().toString();
    }

    public AuthRole getRole() {
        return auth.getRole();
    }

    public UUID getUserId() {
        return auth.getUserId();
    }

}
