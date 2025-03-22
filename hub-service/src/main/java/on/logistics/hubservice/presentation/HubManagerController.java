package on.logistics.hubservice.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import on.logistics.hubservice.application.dtos.request.CreateHubManagerRequestDto;
import on.logistics.hubservice.application.service.HubManagerService;
import on.logistics.hubservice.global.presentation.dtos.CommonResponse;
import on.logistics.hubservice.presentation.dtos.request.CreateHubManagerRequest;
import on.logistics.hubservice.presentation.dtos.response.CreateHubManagerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HubManagerController {

    private final HubManagerService hubManagerService;

    @PostMapping("/api/v1/hubs/manager")
    public ResponseEntity<CommonResponse<CreateHubManagerResponse>> createHubManager(
        @RequestBody @Valid CreateHubManagerRequest createHubManagerRequest) {
        final var requestDto = CreateHubManagerRequestDto.of(createHubManagerRequest);
        final var responseDto = hubManagerService.createHubManager(requestDto);
        return ResponseEntity.ok(CommonResponse.success(responseDto));
    }
}
