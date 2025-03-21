package on.logistics.authservice.infrastructure.feign;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.authservice.global.presentation.dtos.CommonResponse;
import on.logistics.authservice.infrastructure.feign.dtos.UserCreateRequest;
import on.logistics.authservice.infrastructure.feign.dtos.UserCreateResponse;
import on.logistics.authservice.infrastructure.feign.dtos.UserFindByIdResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserClientService {

    private final UserFeignClient userFeignClient;

    public UserCreateResponse createUser(UserCreateRequest request) {
        ResponseEntity<CommonResponse<UserCreateResponse>> responseEntity =
            userFeignClient.createUser(request);

        CommonResponse<UserCreateResponse> commonResponse = responseEntity.getBody();
        if (commonResponse != null) {
            return commonResponse.data();
        } else {
            return null;
        }
    }

    public UserFindByIdResponse findUserById(UUID userId) {
        ResponseEntity<CommonResponse<UserFindByIdResponse>> responseEntity =
            userFeignClient.findUserById(userId);

        CommonResponse<UserFindByIdResponse> commonResponse = responseEntity.getBody();
        if (commonResponse != null) {
            return commonResponse.data();
        } else {
            return null;
        }

    }

}
