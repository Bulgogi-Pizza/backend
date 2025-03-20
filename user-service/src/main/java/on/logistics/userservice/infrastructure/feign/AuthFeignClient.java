package on.logistics.userservice.infrastructure.feign;

import on.logistics.userservice.global.presentation.dtos.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service", url = "localhost:18081")
public interface AuthFeignClient {

    @DeleteMapping("/api/v1/auth/my")
    ResponseEntity<CommonResponse<Void>> deleteAuthByPassport(
        @RequestHeader("X-Passport-Id") String passportId
    );

}
