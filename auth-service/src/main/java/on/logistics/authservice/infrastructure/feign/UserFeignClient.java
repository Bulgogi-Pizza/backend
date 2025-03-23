package on.logistics.authservice.infrastructure.feign;

import java.util.UUID;
import on.logistics.authservice.global.presentation.dtos.CommonResponse;
import on.logistics.authservice.infrastructure.feign.dtos.UserCreateRequest;
import on.logistics.authservice.infrastructure.feign.dtos.UserCreateResponse;
import on.logistics.authservice.infrastructure.feign.dtos.UserFindByIdResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface UserFeignClient {

    @GetMapping("/api/v1/users/{id}")
    ResponseEntity<CommonResponse<UserFindByIdResponse>> findUserById(@PathVariable("id") UUID id);

    @PostMapping("/api/v1/users")
    ResponseEntity<CommonResponse<UserCreateResponse>> createUser(
        @RequestBody UserCreateRequest request);

}
