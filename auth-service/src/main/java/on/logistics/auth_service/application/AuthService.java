package on.logistics.auth_service.application;

import lombok.RequiredArgsConstructor;
import on.logistics.auth_service.application.dtos.AuthSignupRequestDto;
import on.logistics.auth_service.domain.entity.Auth;
import on.logistics.auth_service.domain.repository.AuthRepository;
import on.logistics.auth_service.presentation.dtos.AuthSignupResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    // TODO: UserService 호출 필요
    public AuthSignupResponse signup(AuthSignupRequestDto requestDto) {

        Auth auth = Auth.builder()
            .username(requestDto.username())
            .password(requestDto.password())
            .build();

        // UserService로 호출 해야함
//        UserSignupResponseDto userResponseDto =

        Auth createdAuth = authRepository.save(auth);
//        AuthSignupResponseDto responseDto = AuthSignupResponseDto.from(createdAuth, )
        return null;
    }
}
