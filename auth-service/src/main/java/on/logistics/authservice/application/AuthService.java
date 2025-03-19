package on.logistics.authservice.application;

import on.logistics.authservice.application.dtos.AuthSignupRequestDto;
import on.logistics.authservice.presentation.dtos.AuthSignupResponse;

public interface AuthService {

    AuthSignupResponse signup(AuthSignupRequestDto authRequestDto);
}
