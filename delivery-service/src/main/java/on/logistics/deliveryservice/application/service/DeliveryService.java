package on.logistics.deliveryservice.application.service;

import java.util.UUID;
import on.logistics.deliveryservice.application.dtos.request.CreateDeliveryRequestDto;
import on.logistics.deliveryservice.application.dtos.request.SearchDeliveryRequestDto;
import on.logistics.deliveryservice.application.dtos.request.UpdateAssignManagerRequestDto;
import on.logistics.deliveryservice.application.dtos.request.UpdateDeliveryRequestDto;
import on.logistics.deliveryservice.global.application.dtos.PageDto;
import on.logistics.deliveryservice.presentation.dtos.response.CreateDeliveryResponse;
import on.logistics.deliveryservice.presentation.dtos.response.GetDeliveryResponse;
import on.logistics.deliveryservice.presentation.dtos.response.SearchDeliveryResponse;
import on.logistics.deliveryservice.presentation.dtos.response.UpdateAssignManagerResponse;
import on.logistics.deliveryservice.presentation.dtos.response.UpdateDeliveryResponse;
import on.logistics.deliveryservice.presentation.dtos.response.UpdateDeliveryStatusCancelResponse;
import on.logistics.deliveryservice.presentation.dtos.response.UpdateDeliveryStatusCompanyArriveResponse;
import on.logistics.deliveryservice.presentation.dtos.response.UpdateDeliveryStatusCompanyMovingResponse;
import on.logistics.deliveryservice.presentation.dtos.response.UpdateDeliveryStatusHubArriveResponse;
import on.logistics.deliveryservice.presentation.dtos.response.UpdateDeliveryStatusHubMovingResponse;

public interface DeliveryService {

    CreateDeliveryResponse createDelivery(CreateDeliveryRequestDto requestDto);

    PageDto<SearchDeliveryResponse> searchDelivery(SearchDeliveryRequestDto requestDto);

    GetDeliveryResponse getDelivery(UUID id);

    UpdateDeliveryResponse updateDelivery(UpdateDeliveryRequestDto requestDto);

    void deleteDelivery(UUID id);

    UpdateAssignManagerResponse updateAssignManager(
        UpdateAssignManagerRequestDto updateAssignManagerRequestDto);

    UpdateDeliveryStatusHubMovingResponse updateDeliveryStatusHubMoving(UUID id);

    UpdateDeliveryStatusHubArriveResponse updateDeliveryStatusHubArrive(UUID id);

    UpdateDeliveryStatusCompanyMovingResponse updateDeliveryStatusCompanyMoving(UUID id);

    UpdateDeliveryStatusCompanyArriveResponse updateDeliveryStatusCompanyArrive(UUID id);

    UpdateDeliveryStatusCancelResponse updateDeliveryStatusCancel(UUID id);
}
