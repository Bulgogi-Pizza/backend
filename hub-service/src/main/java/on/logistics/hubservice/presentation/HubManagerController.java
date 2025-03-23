package on.logistics.hubservice.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import on.logistics.hubservice.application.dtos.request.CreateHubManagerRequestDto;
import on.logistics.hubservice.application.dtos.request.ValidHubManagerRequestDto;
import on.logistics.hubservice.application.service.HubManagerService;
import on.logistics.hubservice.global.presentation.dtos.CommonResponse;
import on.logistics.hubservice.presentation.dtos.request.CreateHubManagerRequest;
import on.logistics.hubservice.presentation.dtos.request.ValidHubManagerRequest;
import on.logistics.hubservice.presentation.dtos.response.CreateHubManagerResponse;
import on.logistics.hubservice.presentation.dtos.response.ValidHubManagerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hubs/manager")
public class HubManagerController {

    private final HubManagerService hubManagerService;

    @PostMapping
    public ResponseEntity<CommonResponse<CreateHubManagerResponse>> createHubManager(
        @RequestBody @Valid CreateHubManagerRequest createHubManagerRequest) {
        final var requestDto = CreateHubManagerRequestDto.of(createHubManagerRequest);
        final var responseDto = hubManagerService.createHubManager(requestDto);
        return ResponseEntity.ok(CommonResponse.success(responseDto));
    }

    @PostMapping("/valid")
    public ResponseEntity<CommonResponse<ValidHubManagerResponse>> validHubManager(
        @RequestBody @Valid ValidHubManagerRequest validHubManagerRequest) {
        final var requestDto = ValidHubManagerRequestDto.of(validHubManagerRequest);
        final var responseDto = hubManagerService.validHubManager(requestDto);
        return ResponseEntity.ok(CommonResponse.success(responseDto));
    }
}
