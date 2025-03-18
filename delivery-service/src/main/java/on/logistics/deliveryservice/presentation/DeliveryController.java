package on.logistics.deliveryservice.presentation;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.deliveryservice.application.dtos.request.CreateDeliveryRequestDto;
import on.logistics.deliveryservice.application.dtos.request.UpdateDeliveryRequestDto;
import on.logistics.deliveryservice.application.service.DeliveryService;
import on.logistics.deliveryservice.global.presentation.dtos.CommonResponse;
import on.logistics.deliveryservice.presentation.dtos.request.CreateDeliveryRequest;
import on.logistics.deliveryservice.presentation.dtos.request.UpdateDeliveryRequest;
import on.logistics.deliveryservice.presentation.dtos.response.CreateDeliveryResponse;
import on.logistics.deliveryservice.presentation.dtos.response.UpdateDeliveryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<CommonResponse<CreateDeliveryResponse>> createDelivery(
        @Valid @RequestBody CreateDeliveryRequest createDeliveryRequest) {
        final CreateDeliveryRequestDto requestDto = CreateDeliveryRequest.from(
            createDeliveryRequest);
        CreateDeliveryResponse response = deliveryService.createDelivery(requestDto);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<UpdateDeliveryResponse>> updateDelivery(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateDeliveryRequest updateDeliveryRequest) {
        final UpdateDeliveryRequestDto requestDto = UpdateDeliveryRequest.from(id,
            updateDeliveryRequest);
        UpdateDeliveryResponse response = deliveryService.updateDelivery(requestDto);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<Void>> deleteDelivery(
        @PathVariable UUID id
    ) {
        deliveryService.deleteDelivery(id);
        return ResponseEntity.ok(CommonResponse.success());
    }
}
