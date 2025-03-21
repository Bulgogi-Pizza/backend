package on.logistics.userservice.infrastructure.feign;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthClientService {

    private final AuthFeignClient authFeignClient;

    public void deleteAuthByPassport(String passportId) {
        authFeignClient.deleteAuthByPassport(passportId);
    }
}
