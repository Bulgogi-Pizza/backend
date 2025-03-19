package on.logistics.userservice.application.service;

import java.util.UUID;
import on.logistics.userservice.application.dtos.UserCreateRequestDto;
import on.logistics.userservice.presentation.dtos.UserCreateResponse;
import on.logistics.userservice.presentation.dtos.UserFindByIdResponse;

public interface UserService {

    UserCreateResponse createUser(UserCreateRequestDto requestDto);

    UserFindByIdResponse findUserById(UUID id);
}
