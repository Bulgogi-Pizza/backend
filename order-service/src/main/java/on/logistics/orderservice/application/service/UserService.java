package on.logistics.orderservice.application.service;

import java.util.UUID;
import on.logistics.orderservice.infrastructure.clients.user.dtos.FindUserSlackEmailByUserIdResponse;

public interface UserService {

    FindUserSlackEmailByUserIdResponse findUserSlackEmailByUserId(UUID userId);
}
