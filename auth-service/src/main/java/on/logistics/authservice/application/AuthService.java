package on.logistics.authservice.application;

import jakarta.servlet.http.HttpServletRequest;
import on.logistics.authservice.application.dtos.AuthSignupRequestDto;
import on.logistics.authservice.presentation.dtos.AuthSignupResponse;
import on.logistics.authservice.presentation.dtos.AuthValidateResponse;

public interface AuthService {

    AuthSignupResponse signup(AuthSignupRequestDto authRequestDto);

    AuthValidateResponse validate(HttpServletRequest request);
}
