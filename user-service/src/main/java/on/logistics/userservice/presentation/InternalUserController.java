package on.logistics.userservice.presentation;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.userservice.application.service.UserService;
import on.logistics.userservice.global.presentation.dtos.CommonResponse;
import on.logistics.userservice.presentation.dtos.FindByIdUserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j(topic = "InternalUserController")
@RequiredArgsConstructor
@RequestMapping("/internal/v1/users")
public class InternalUserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<FindByIdUserResponse>> findUserById(
        @PathVariable("id") String id,
        HttpServletRequest request
    ) {

        FindByIdUserResponse response = userService.findUserByIdInternal(
            UUID.fromString(id),
            request
        );
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    @PutMapping("/withdraw/{id}")
    public ResponseEntity<CommonResponse<Void>> withdrawUserByUserId(
        @PathVariable("id") UUID id,
        HttpServletRequest request
    ) {
        log.info("withdrawUserByUserId, {}", id.toString());
        userService.withdrawUserByUserId(id, request);

        return ResponseEntity.ok(CommonResponse.success());
    }

}
