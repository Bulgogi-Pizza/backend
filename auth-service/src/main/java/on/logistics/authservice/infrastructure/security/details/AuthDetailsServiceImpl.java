package on.logistics.authservice.infrastructure.security.details;

import lombok.RequiredArgsConstructor;
import on.logistics.authservice.domain.entity.Auth;
import on.logistics.authservice.domain.repository.AuthRepository;
import on.logistics.authservice.domain.vo.Username;
import on.logistics.authservice.exception.AuthException;
import on.logistics.authservice.exception.AuthExceptionCode;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDetailsServiceImpl implements AuthDetailsService {

    private final AuthRepository authRepository;

    @Override
    public AuthDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Auth auth = authRepository.findByUsername(new Username(username))
            .orElseThrow(() -> new AuthException(AuthExceptionCode.AUTH_IS_NOT_FOUND));

        return new AuthDetailsImpl(auth);
    }

    @Override
    public AuthDetails loadAuthByUsername(String username) throws UsernameNotFoundException {

        return loadUserByUsername(username);
    }
}
