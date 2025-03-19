package on.logistics.userservice.application.service;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import on.logistics.userservice.application.dtos.CreateUserDto;
import on.logistics.userservice.application.dtos.SearchUserDto;
import on.logistics.userservice.application.dtos.UpdateUserDto;
import on.logistics.userservice.global.application.dtos.PageDto;
import on.logistics.userservice.presentation.dtos.CreateUserResponse;
import on.logistics.userservice.presentation.dtos.FindByIdUserResponse;
import on.logistics.userservice.presentation.dtos.FindMyUserResponse;
import on.logistics.userservice.presentation.dtos.SearchUserResponse;
import on.logistics.userservice.presentation.dtos.UpdateUserResponse;

public interface UserService {

    CreateUserResponse createUser(CreateUserDto requestDto);

    FindByIdUserResponse findUserById(UUID id);

    FindMyUserResponse findMyUser(HttpServletRequest request);

    PageDto<SearchUserResponse> searchUser(SearchUserDto requestDto);

    UpdateUserResponse updateUser(HttpServletRequest request, UpdateUserDto requestDto);
}
