package on.logistics.userservice.presentation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.userservice.application.dtos.CreateUserDto;
import on.logistics.userservice.application.dtos.GetSlackEmailByIdResponseDto;
import on.logistics.userservice.application.dtos.SearchUserDto;
import on.logistics.userservice.application.dtos.UpdateUserAdminDto;
import on.logistics.userservice.application.dtos.UpdateUserDto;
import on.logistics.userservice.application.service.UserService;
import on.logistics.userservice.global.application.dtos.PageDto;
import on.logistics.userservice.global.presentation.dtos.CommonResponse;
import on.logistics.userservice.presentation.dtos.CreateUserRequest;
import on.logistics.userservice.presentation.dtos.CreateUserResponse;
import on.logistics.userservice.presentation.dtos.FindByIdUserResponse;
import on.logistics.userservice.presentation.dtos.FindMyUserResponse;
import on.logistics.userservice.presentation.dtos.SearchUserResponse;
import on.logistics.userservice.presentation.dtos.UpdateUserAdminRequest;
import on.logistics.userservice.presentation.dtos.UpdateUserAdminResponse;
import on.logistics.userservice.presentation.dtos.UpdateUserRequest;
import on.logistics.userservice.presentation.dtos.UpdateUserResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        CreateUserDto dto = CreateUserDto.from(request);
        CreateUserResponse response = userService.createUser(dto);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    @PatchMapping("/my")
    public ResponseEntity<CommonResponse<UpdateUserResponse>> updateUser(
        HttpServletRequest request,
        @RequestBody UpdateUserRequest updateUserRequest
    ) {
        UpdateUserDto dto = UpdateUserDto.from(updateUserRequest);
        UpdateUserResponse response = userService.updateUser(request, dto);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    @GetMapping("/my")
    public ResponseEntity<CommonResponse<FindMyUserResponse>> findMyUser(
        HttpServletRequest request
    ) {
        FindMyUserResponse response = userService.findMyUser(request);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<FindByIdUserResponse>> findUserById(
        @PathVariable("id") String id
    ) {
        FindByIdUserResponse response = userService.findUserById(UUID.fromString(id));
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    @GetMapping
    public ResponseEntity<CommonResponse<PageDto<SearchUserResponse>>> getUser(
        @RequestParam(required = false) String nickname,
        @RequestParam(required = false) String slackEmail,
        @PageableDefault Pageable pageable
    ) {
        SearchUserDto dto = SearchUserDto.from(nickname, slackEmail, pageable);
        PageDto<SearchUserResponse> response = userService.searchUser(dto);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    @GetMapping("/{id}/slack-email")
    public ResponseEntity<CommonResponse<GetSlackEmailByIdResponseDto>> getSlackEmailById(
        @PathVariable UUID id,
        HttpServletRequest request
    ) {
        GetSlackEmailByIdResponseDto response = userService.getSlackEmailById(id, request);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    // TODO: 사용자 ID 암호화 필요
    @PatchMapping("/{id}")
    public ResponseEntity<CommonResponse<UpdateUserAdminResponse>> updateUserAdmin(
        @PathVariable UUID id,
        @RequestBody UpdateUserAdminRequest updateUserAdminRequest
    ) {
        UpdateUserAdminDto dto = UpdateUserAdminDto.from(id, updateUserAdminRequest);
        UpdateUserAdminResponse response = userService.updateUserAdmin(dto);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    // TODO: FeignClient 연결은 확인, auth에서 SpringSecurity에 걸림
    @DeleteMapping("/my")
    public ResponseEntity<CommonResponse<Void>> deleteUser(
        HttpServletRequest request,
        HttpServletResponse response
    ) {
        userService.deleteUser(request, response);
        return ResponseEntity.ok(CommonResponse.success());
    }
}
