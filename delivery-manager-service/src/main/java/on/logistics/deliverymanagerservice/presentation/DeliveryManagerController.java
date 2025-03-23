package on.logistics.deliverymanagerservice.presentation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.deliverymanagerservice.application.dtos.AssignDeliveryManagerRequestDto;
import on.logistics.deliverymanagerservice.application.dtos.CreateDeliveryManagerRequestDto;
import on.logistics.deliverymanagerservice.application.dtos.SearchDeliveryManagerRequestDto;
import on.logistics.deliverymanagerservice.application.dtos.UpdateDeliveryManagerRequestDto;
import on.logistics.deliverymanagerservice.application.dtos.ValidDeliveryManagerRequestDto;
import on.logistics.deliverymanagerservice.application.service.DeliveryManagerService;
import on.logistics.deliverymanagerservice.global.application.dtos.PageDto;
import on.logistics.deliverymanagerservice.global.presentation.dtos.CommonResponse;
import on.logistics.deliverymanagerservice.presentation.dtos.request.AssignDeliveryManagerRequest;
import on.logistics.deliverymanagerservice.presentation.dtos.request.CreateDeliveryManagerRequest;
import on.logistics.deliverymanagerservice.presentation.dtos.request.UpdateDeliveryManagerRequest;
import on.logistics.deliverymanagerservice.presentation.dtos.request.ValidDeliveryManagerRequest;
import on.logistics.deliverymanagerservice.presentation.dtos.response.AssignDeliveryManagerResponse;
import on.logistics.deliverymanagerservice.presentation.dtos.response.CreateDeliveryManagerResponse;
import on.logistics.deliverymanagerservice.presentation.dtos.response.GetDeliveryManagerResponse;
import on.logistics.deliverymanagerservice.presentation.dtos.response.SearchDeliveryManagerResponse;
import on.logistics.deliverymanagerservice.presentation.dtos.response.UpdateDeliveryManagerResponse;
import on.logistics.deliverymanagerservice.presentation.dtos.response.ValidDeliveryManagerResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/delivery-managers")
public class DeliveryManagerController {

    private final DeliveryManagerService deliveryManagerService;

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<GetDeliveryManagerResponse>> getDeliveryManager(
        @PathVariable UUID id,
        HttpServletRequest passportRequest) {
        final var responseDto = deliveryManagerService.getDeliveryManager(id, passportRequest);
        return ResponseEntity.ok(CommonResponse.success(responseDto));
    }

    @GetMapping("/search")
    public ResponseEntity<CommonResponse<PageDto<SearchDeliveryManagerResponse>>> searchDeliveryManager(
        @RequestParam(required = false) String keyword,
        @RequestParam(required = false) String hubType,
        @RequestParam(required = false) String deliveryType,
        @PageableDefault Pageable pageable,
        HttpServletRequest passportRequest) {
        final var requestDto = SearchDeliveryManagerRequestDto.of(keyword, hubType, deliveryType,
            pageable, passportRequest);
        final var responseDto = deliveryManagerService.searchDeliveryManager(requestDto);
        return ResponseEntity.ok(CommonResponse.success(responseDto));
    }

    @PostMapping
    public ResponseEntity<CommonResponse<CreateDeliveryManagerResponse>> createDeliveryManager(
        @RequestBody @Valid CreateDeliveryManagerRequest createDeliveryManagerRequest,
        HttpServletRequest passportRequest) {
        final var requestDto = CreateDeliveryManagerRequestDto.of(createDeliveryManagerRequest,
            passportRequest);
        final var responseDto = deliveryManagerService.createDeliveryManager(requestDto);
        return ResponseEntity.ok(CommonResponse.success(responseDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<UpdateDeliveryManagerResponse>> updateDeliveryManager(
        @PathVariable UUID id,
        @RequestBody @Valid UpdateDeliveryManagerRequest updateDeliveryManagerRequest,
        HttpServletRequest passportRequest) {
        final var requestDto = UpdateDeliveryManagerRequestDto.of(id, updateDeliveryManagerRequest,
            passportRequest);
        final var responseDto = deliveryManagerService.updateDeliveryManager(requestDto);
        return ResponseEntity.ok(CommonResponse.success(responseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<Void>> deleteDeliveryManager(
        @PathVariable UUID id,
        HttpServletRequest passportRequest) {
        deliveryManagerService.deleteDeliveryManager(id, passportRequest);
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
