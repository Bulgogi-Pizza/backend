package on.logistics.userservice.application.service;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.userservice.application.dtos.CreateUserDto;
import on.logistics.userservice.application.dtos.SearchUserDto;
import on.logistics.userservice.application.dtos.UpdateUserDto;
import on.logistics.userservice.domain.entity.User;
import on.logistics.userservice.domain.repository.UserRepository;
import on.logistics.userservice.exception.UserException;
import on.logistics.userservice.exception.UserExceptionCode;
import on.logistics.userservice.global.application.dtos.PageDto;
import on.logistics.userservice.global.domain.Passport;
import on.logistics.userservice.global.util.PassportUtil;
import on.logistics.userservice.presentation.dtos.CreateUserResponse;
import on.logistics.userservice.presentation.dtos.FindByIdUserResponse;
import on.logistics.userservice.presentation.dtos.FindMyUserResponse;
import on.logistics.userservice.presentation.dtos.SearchUserResponse;
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

    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserDto requestDto) {

        User user = User.create(requestDto);
        // TODO: createdBy 해결 필
        user.createUser();
        User savedUser = userRepository.save(user);
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
    public PageDto<SearchUserResponse> searchUser(SearchUserDto requestDto) {
        Page<User> users = userRepository.searchUser(requestDto);
        Page<SearchUserResponse> responsePage = users.map(SearchUserResponse::from);
        return PageDto.from(responsePage);
    }

    @Override
    @Transactional
    public UpdateUserResponse updateUser(HttpServletRequest request, UpdateUserDto requestDto) {
        Passport passport = passportUtil.getPassportByHttpServletRequest(request);
        UUID id = passport.getUserId();

        User user = findByIdOrElseThrow(id);

        user.updateNickname(requestDto.nickname());
        user.updateSlackEmail(requestDto.slackEmail());

        return UpdateUserResponse.from(user);
    }

    private User findByIdOrElseThrow(UUID id) {
        return userRepository.findById(id).orElseThrow(
            () -> new UserException(UserExceptionCode.USER_IS_NOT_FOUND)
        );
    }


}
