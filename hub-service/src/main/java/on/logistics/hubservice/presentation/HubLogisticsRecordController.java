package on.logistics.hubservice.presentation;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.hubservice.application.dtos.request.StorageLogisticsRequestDto;
import on.logistics.hubservice.application.service.HubLogisticsRecordService;
import on.logistics.hubservice.global.presentation.dtos.CommonResponse;
import on.logistics.hubservice.presentation.dtos.request.StorageLogisticsRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hubs")
public class HubLogisticsRecordController {

    private final HubLogisticsRecordService hubLogisticsRecordService;

    @PostMapping("/{hubId}/storage")
    ResponseEntity<CommonResponse<Void>> storageProduct(
        @PathVariable UUID hubId,
        @RequestBody StorageLogisticsRequest storageLogisticsRequest) {
        final var requestDto = StorageLogisticsRequestDto.of(hubId, storageLogisticsRequest);
        hubLogisticsRecordService.storage(requestDto);
        return ResponseEntity.ok(CommonResponse.success());
    }
}
