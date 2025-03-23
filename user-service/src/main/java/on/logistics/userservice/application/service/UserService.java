package on.logistics.userservice.application.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;
import on.logistics.userservice.application.dtos.CreateUserDto;
import on.logistics.userservice.application.dtos.SearchUserDto;
import on.logistics.userservice.application.dtos.UpdateUserAdminDto;
import on.logistics.userservice.application.dtos.UpdateUserDto;
import on.logistics.userservice.global.application.dtos.PageDto;
import on.logistics.userservice.presentation.dtos.CreateUserResponse;
import on.logistics.userservice.presentation.dtos.FindByIdUserResponse;
import on.logistics.userservice.presentation.dtos.FindMyUserResponse;
import on.logistics.userservice.presentation.dtos.SearchUserResponse;
import on.logistics.userservice.presentation.dtos.UpdateUserAdminResponse;
import on.logistics.userservice.presentation.dtos.UpdateUserResponse;

public interface UserService {

    CreateUserResponse createUser(CreateUserDto requestDto, HttpServletRequest servletRequest);

    FindByIdUserResponse findUserById(UUID id, HttpServletRequest servletRequest);

    FindMyUserResponse findMyUser(HttpServletRequest request);

    PageDto<SearchUserResponse> searchUser(SearchUserDto requestDto,
        HttpServletRequest servletRequest);

    UpdateUserResponse updateUser(UpdateUserDto requestDto, HttpServletRequest request);

    void deleteUser(HttpServletRequest request, HttpServletResponse response);

    UpdateUserAdminResponse updateUserAdmin(UpdateUserAdminDto dto,
        HttpServletRequest servletRequest);

    FindByIdUserResponse findUserByIdInternal(UUID uuid, HttpServletRequest request);

    void withdrawUserByUserId(UUID id, HttpServletRequest request);
}
