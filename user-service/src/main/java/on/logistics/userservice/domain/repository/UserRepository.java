package on.logistics.userservice.domain.repository;

import java.util.Optional;
import java.util.UUID;
import on.logistics.userservice.application.dtos.SearchUserDto;
import on.logistics.userservice.domain.entity.User;
import org.springframework.data.domain.Page;

public interface UserRepository {

    User save(User user);

    void delete(User user);

    Optional<User> findById(UUID id);

    Page<User> searchUser(SearchUserDto requestDto);
}
