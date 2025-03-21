package on.logistics.userservice.infrastructure.querydsl;

import on.logistics.userservice.application.dtos.SearchUserDto;
import on.logistics.userservice.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface UserQueryRepository {

    Page<User> searchUser(SearchUserDto requestDto);
}
