package on.logistics.orderservice.infrastructure.clients.user;

import feign.Response;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.orderservice.application.service.UserService;
import on.logistics.orderservice.global.utils.FeignClientResponseUtils;
import on.logistics.orderservice.infrastructure.clients.user.dtos.FindUserSlackEmailByUserIdResponse;
import on.logistics.orderservice.infrastructure.clients.user.feign.UserServiceFeignClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserServiceFeignClient userServiceFeignClient;


    @Override
    public FindUserSlackEmailByUserIdResponse findUserSlackEmailByUserId(UUID userId) {
        log.info("Getting user by id: {}", userId);
        Response response = userServiceFeignClient.findUserSlackEmailByUserId(userId);
        return FeignClientResponseUtils.getBody(response, FindUserSlackEmailByUserIdResponse.class);
    }
}
