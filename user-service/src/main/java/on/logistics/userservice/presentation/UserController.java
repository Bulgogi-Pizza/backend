package on.logistics.userservice.presentation;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.userservice.application.dtos.UserCreateRequestDto;
import on.logistics.userservice.application.service.UserService;
import on.logistics.userservice.global.presentation.dtos.CommonResponse;
import on.logistics.userservice.presentation.dtos.UserCreateRequest;
import on.logistics.userservice.presentation.dtos.UserCreateResponse;
import on.logistics.userservice.presentation.dtos.UserFindByIdResponse;
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
    public ResponseEntity<CommonResponse<UserCreateResponse>> create(
        @RequestBody UserCreateRequest request
    ) {
        final var requestDto = UserCreateRequestDto.from(request);
        final var response = userService.createUser(requestDto);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<UserFindByIdResponse>> findById(
        @PathVariable("id") String id
    ) {
        final var response = userService.findUserById(UUID.fromString(id));
        return ResponseEntity.ok(CommonResponse.success(response));
    }

}
