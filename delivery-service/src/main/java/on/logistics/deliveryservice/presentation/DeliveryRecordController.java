package on.logistics.deliveryservice.presentation;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.deliveryservice.application.dtos.request.CreateDeliveryRecordRequestDto;
import on.logistics.deliveryservice.application.dtos.request.UpdateDeliveryRecordRequestDto;
import on.logistics.deliveryservice.application.service.DeliveryRecordService;
import on.logistics.deliveryservice.global.presentation.dtos.CommonResponse;
import on.logistics.deliveryservice.presentation.dtos.request.CreateDeliveryRecordRequest;
import on.logistics.deliveryservice.presentation.dtos.request.UpdateDeliveryRecordRequest;
import on.logistics.deliveryservice.presentation.dtos.response.CreateDeliveryRecordResponse;
import on.logistics.deliveryservice.presentation.dtos.response.UpdateDeliveryRecordResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/delivery/record")
public class DeliveryRecordController {

    private final DeliveryRecordService deliveryRecordService;

    @PostMapping
    public ResponseEntity<CommonResponse<CreateDeliveryRecordResponse>> createDeliveryRecord(
        @Valid @RequestBody CreateDeliveryRecordRequest createDeliveryRecordRequest
    ) {
        CreateDeliveryRecordRequestDto requestDto = CreateDeliveryRecordRequestDto.from(
            createDeliveryRecordRequest);
        CreateDeliveryRecordResponse response = deliveryRecordService.createDeliveryRecord(
            requestDto);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    @PutMapping("/actual/{id}")
    public ResponseEntity<CommonResponse<UpdateDeliveryRecordResponse>> updateActualDeliveryRecord(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateDeliveryRecordRequest updateDeliveryRecordRequest
    ) {
        UpdateDeliveryRecordRequestDto requestDto = UpdateDeliveryRecordRequestDto.from(
            id, updateDeliveryRecordRequest);
        UpdateDeliveryRecordResponse response = deliveryRecordService.updateActualDeliveryRecord(
            requestDto);
        return ResponseEntity.ok(CommonResponse.success(response));
    }
}
