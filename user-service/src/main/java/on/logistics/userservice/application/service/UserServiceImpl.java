package on.logistics.userservice.application.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.userservice.application.dtos.CreateUserDto;
import on.logistics.userservice.application.dtos.SearchUserDto;
import on.logistics.userservice.application.dtos.UpdateUserAdminDto;
import on.logistics.userservice.application.dtos.UpdateUserDto;
import on.logistics.userservice.domain.entity.User;
import on.logistics.userservice.domain.repository.UserRepository;
import on.logistics.userservice.exception.UserException;
import on.logistics.userservice.exception.UserExceptionCode;
import on.logistics.userservice.global.application.dtos.PageDto;
import on.logistics.userservice.global.domain.Passport;
import on.logistics.userservice.global.util.PassportUtil;
import on.logistics.userservice.infrastructure.feign.AuthClientService;
import on.logistics.userservice.presentation.dtos.CreateUserResponse;
import on.logistics.userservice.presentation.dtos.FindByIdUserResponse;
import on.logistics.userservice.presentation.dtos.FindMyUserResponse;
import on.logistics.userservice.presentation.dtos.SearchUserResponse;
import on.logistics.userservice.presentation.dtos.UpdateUserAdminResponse;
import on.logistics.userservice.presentation.dtos.UpdateUserResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "UserServiceImpl")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PassportUtil passportUtil;
    private final AuthClientService authClientService;

    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserDto dto) {

        User user = User.create(dto);

        User savedUser = userRepository.save(user);
        savedUser.setId(savedUser.getId());

        return CreateUserResponse.from(savedUser);
    }

    @Override
    public FindByIdUserResponse findUserById(UUID id) {
        User user = findByIdOrElseThrow(id);
        return FindByIdUserResponse.from(user);
    }

    @Override
    public FindMyUserResponse findMyUser(HttpServletRequest request) {
        Passport passport = passportUtil.getPassportByHttpServletRequest(request);
        UUID id = passport.getUserId();

        User user = findByIdOrElseThrow(id);
        return FindMyUserResponse.from(user);
    }

    @Override
    public PageDto<SearchUserResponse> searchUser(SearchUserDto dto) {
        Page<User> users = userRepository.searchUser(dto);
        Page<SearchUserResponse> responsePage = users.map(SearchUserResponse::from);
        return PageDto.from(responsePage);
    }

    @Override
    @Transactional
    public UpdateUserResponse updateUser(HttpServletRequest request, UpdateUserDto dto) {
        Passport passport = passportUtil.getPassportByHttpServletRequest(request);
        UUID id = passport.getUserId();

        User user = findByIdOrElseThrow(id);

        user.updateNickname(dto.nickname());
        user.updateSlackEmail(dto.slackEmail());

        return UpdateUserResponse.from(user);
    }

    @Override
    public void deleteUser(
        HttpServletRequest request,
        HttpServletResponse response
    ) {
        // TODO: deletedBy, deletedAt 해결 필
        Passport passport = passportUtil.getPassportByHttpServletRequest(request);
        String passportId = request.getParameter("X-Passport-Id");

        // TODO: feignClient 해결 필
//        authClientService.deleteAuthByPassport(passportId);

        User user = findByIdOrElseThrow(passport.getUserId());
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public UpdateUserAdminResponse updateUserAdmin(UpdateUserAdminDto dto) {
        User user = findByIdOrElseThrow(dto.userId());

        user.updateNickname(dto.nickname());
        user.updateSlackEmail(dto.slackEmail());

        return UpdateUserAdminResponse.from(user);
    }

    private User findByIdOrElseThrow(UUID id) {
        return userRepository.findById(id).orElseThrow(
            () -> new UserException(UserExceptionCode.USER_IS_NOT_FOUND)
        );
    }


}
