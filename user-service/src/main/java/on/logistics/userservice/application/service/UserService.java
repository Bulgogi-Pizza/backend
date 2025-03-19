package on.logistics.userservice.application.service;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import on.logistics.userservice.application.dtos.CreateUserRequestDto;
import on.logistics.userservice.presentation.dtos.CreateUserResponse;
import on.logistics.userservice.presentation.dtos.FindByIdUserResponse;
import on.logistics.userservice.presentation.dtos.FindMyUserResponse;

public interface UserService {

    CreateUserResponse createUser(CreateUserRequestDto requestDto);

    FindByIdUserResponse findUserById(UUID id);

    FindMyUserResponse findMyUser(HttpServletRequest request);
}
