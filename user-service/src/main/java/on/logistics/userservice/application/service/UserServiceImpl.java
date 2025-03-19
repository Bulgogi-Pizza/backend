package on.logistics.userservice.application.service;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.userservice.application.dtos.CreateUserRequestDto;
import on.logistics.userservice.domain.entity.User;
import on.logistics.userservice.domain.repository.UserRepository;
import on.logistics.userservice.exception.UserException;
import on.logistics.userservice.exception.UserExceptionCode;
import on.logistics.userservice.global.domain.Passport;
import on.logistics.userservice.global.util.PassportUtil;
import on.logistics.userservice.presentation.dtos.CreateUserResponse;
import on.logistics.userservice.presentation.dtos.FindByIdUserResponse;
import on.logistics.userservice.presentation.dtos.FindMyUserResponse;
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
    public CreateUserResponse createUser(CreateUserRequestDto requestDto) {

        User user = User.create(requestDto);
        // TODO: createdBy 해결 필
        user.createUser();
        User savedUser = userRepository.save(user);
        return CreateUserResponse.from(savedUser);
    }

    @Override
    public FindByIdUserResponse findUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new UserException(UserExceptionCode.USER_IS_NOT_FOUND)
        );
        return FindByIdUserResponse.from(user);
    }

    @Override
    public FindMyUserResponse findMyUser(HttpServletRequest request) {
        Passport passport = passportUtil.getPassportByHttpServletRequest(request);

        UUID userId = passport.getUserId();

        User user = userRepository.findById(userId).orElseThrow(
            () -> new UserException(UserExceptionCode.USER_IS_NOT_FOUND)
        );
        return FindMyUserResponse.from(user);
    }


}
