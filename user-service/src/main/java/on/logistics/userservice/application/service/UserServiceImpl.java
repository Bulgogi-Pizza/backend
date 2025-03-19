package on.logistics.userservice.application.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.userservice.application.dtos.UserCreateRequestDto;
import on.logistics.userservice.domain.entity.User;
import on.logistics.userservice.domain.repository.UserRepository;
import on.logistics.userservice.exception.UserException;
import on.logistics.userservice.exception.UserExceptionCode;
import on.logistics.userservice.presentation.dtos.UserCreateResponse;
import on.logistics.userservice.presentation.dtos.UserFindByIdResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "UserServiceImpl")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserCreateResponse createUser(UserCreateRequestDto requestDto) {

        User user = User.create(requestDto);
        // TODO: createdBy 해결 필
        user.createUser();
        User savedUser = userRepository.save(user);
        return UserCreateResponse.from(savedUser);
    }

    @Override
    public UserFindByIdResponse findUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new UserException(UserExceptionCode.USER_IS_NOT_FOUND)
        );
        return UserFindByIdResponse.from(user);
    }
}
