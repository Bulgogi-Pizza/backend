package on.logistics.deliverymanagerservice.presentation;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.deliverymanagerservice.application.dtos.AssignDeliveryManagerRequestDto;
import on.logistics.deliverymanagerservice.application.dtos.CreateDeliveryManagerRequestDto;
import on.logistics.deliverymanagerservice.application.dtos.UpdateDeliveryManagerRequestDto;
import on.logistics.deliverymanagerservice.application.dtos.ValidDeliveryManagerRequestDto;
import on.logistics.deliverymanagerservice.application.service.DeliveryManagerService;
import on.logistics.deliverymanagerservice.global.presentation.dtos.CommonResponse;
import on.logistics.deliverymanagerservice.presentation.dtos.request.AssignDeliveryManagerRequest;
import on.logistics.deliverymanagerservice.presentation.dtos.request.CreateDeliveryManagerRequest;
import on.logistics.deliverymanagerservice.presentation.dtos.request.UpdateDeliveryManagerRequest;
import on.logistics.deliverymanagerservice.presentation.dtos.request.ValidDeliveryManagerRequest;
import on.logistics.deliverymanagerservice.presentation.dtos.response.AssignDeliveryManagerResponse;
import on.logistics.deliverymanagerservice.presentation.dtos.response.CreateDeliveryManagerResponse;
import on.logistics.deliverymanagerservice.presentation.dtos.response.GetDeliveryManagerResponse;
import on.logistics.deliverymanagerservice.presentation.dtos.response.UpdateDeliveryManagerResponse;
import on.logistics.deliverymanagerservice.presentation.dtos.response.ValidDeliveryManagerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/delivery-managers")
public class DeliveryManagerController {

    private final DeliveryManagerService deliveryManagerService;

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<GetDeliveryManagerResponse>> getDeliveryManager(
        @PathVariable UUID id) {
        final var responseDto = deliveryManagerService.getDeliveryManager(id);
        return ResponseEntity.ok(CommonResponse.success(responseDto));
    }

    @PostMapping
    public ResponseEntity<CommonResponse<CreateDeliveryManagerResponse>> createDeliveryManager(
        @RequestBody @Valid CreateDeliveryManagerRequest createDeliveryManagerRequest) {
        final var requestDto = CreateDeliveryManagerRequestDto.of(createDeliveryManagerRequest);
        final var responseDto = deliveryManagerService.createDeliveryManager(requestDto);
        return ResponseEntity.ok(CommonResponse.success(responseDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<UpdateDeliveryManagerResponse>> updateDeliveryManager(
        @PathVariable UUID id,
        @RequestBody @Valid UpdateDeliveryManagerRequest updateDeliveryManagerRequest) {
        final var requestDto = UpdateDeliveryManagerRequestDto.of(id, updateDeliveryManagerRequest);
        final var responseDto = deliveryManagerService.updateDeliveryManager(requestDto);
        return ResponseEntity.ok(CommonResponse.success(responseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<Void>> deleteDeliveryManager(@PathVariable UUID id) {
        deliveryManagerService.deleteDeliveryManager(id);
        return ResponseEntity.ok(CommonResponse.success());
    }

    @PostMapping("/assign")
    public ResponseEntity<CommonResponse<AssignDeliveryManagerResponse>> assignDeliveryManager(
        @RequestBody @Valid AssignDeliveryManagerRequest assignDeliveryManagerRequest) {
        final var requestDto = AssignDeliveryManagerRequestDto.of(assignDeliveryManagerRequest);
        final var responseDto = deliveryManagerService.assignDeliveryManager(requestDto);
        return ResponseEntity.ok(CommonResponse.success(responseDto));
    }

    @PostMapping("/valid")
    public ResponseEntity<CommonResponse<ValidDeliveryManagerResponse>> validDeliveryManager(
        @RequestBody @Valid ValidDeliveryManagerRequest validDeliveryManagerRequest) {
        final var requestDto = ValidDeliveryManagerRequestDto.of(validDeliveryManagerRequest);
        final var responseDto = deliveryManagerService.validDeliveryManager(requestDto);
        return ResponseEntity.ok(CommonResponse.success(responseDto));
    }
}
