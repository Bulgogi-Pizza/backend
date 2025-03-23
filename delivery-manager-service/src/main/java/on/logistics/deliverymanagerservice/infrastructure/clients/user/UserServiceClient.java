package on.logistics.deliverymanagerservice.infrastructure.clients.user;

import on.logistics.deliverymanagerservice.infrastructure.clients.user.feign.dtos.response.GetUserInfoResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserServiceClient {

    GetUserInfoResponse getUserInfo(String id);
}
