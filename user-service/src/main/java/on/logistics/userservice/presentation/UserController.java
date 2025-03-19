package on.logistics.userservice.presentation;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.userservice.application.dtos.CreateUserRequestDto;
import on.logistics.userservice.application.service.UserService;
import on.logistics.userservice.global.presentation.dtos.CommonResponse;
import on.logistics.userservice.presentation.dtos.CreateUserRequest;
import on.logistics.userservice.presentation.dtos.CreateUserResponse;
import on.logistics.userservice.presentation.dtos.FindByIdUserResponse;
import on.logistics.userservice.presentation.dtos.FindMyUserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<CommonResponse<CreateUserResponse>> createUser(
        @RequestBody CreateUserRequest request
    ) {
        final var requestDto = CreateUserRequestDto.from(request);
        final var response = userService.createUser(requestDto);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<FindByIdUserResponse>> findUserById(
        @PathVariable("id") String id
    ) {
        final var response = userService.findUserById(UUID.fromString(id));
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    @GetMapping("/my")
    public ResponseEntity<CommonResponse<FindMyUserResponse>> findMyUser(
        HttpServletRequest request
    ) {
        final var response = userService.findMyUser(request);
        return ResponseEntity.ok(CommonResponse.success(response));
    }


}
