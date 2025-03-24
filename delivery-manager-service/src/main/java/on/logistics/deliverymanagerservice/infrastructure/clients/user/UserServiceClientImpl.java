package on.logistics.deliverymanagerservice.infrastructure.clients.user;

import feign.Response;
import lombok.RequiredArgsConstructor;
import on.logistics.deliverymanagerservice.global.util.FeignClientResponseUtils;
import on.logistics.deliverymanagerservice.infrastructure.clients.user.feign.UserServiceFeignClient;
import on.logistics.deliverymanagerservice.infrastructure.clients.user.feign.dtos.response.GetUserInfoResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceClientImpl implements UserServiceClient {

    private final UserServiceFeignClient userServiceFeignClient;

    @Override
    public GetUserInfoResponse getUserInfo(String id) {
        Response response = userServiceFeignClient.getUserInfo(id);
        return FeignClientResponseUtils.getBody(response, GetUserInfoResponse.class);
    }
}
