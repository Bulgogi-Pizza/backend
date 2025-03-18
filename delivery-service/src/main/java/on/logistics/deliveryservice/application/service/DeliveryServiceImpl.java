package on.logistics.deliveryservice.application.service;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.deliveryservice.application.dtos.DeliveryHubInfoDto;
import on.logistics.deliveryservice.application.dtos.DeliveryUserInfoDto;
import on.logistics.deliveryservice.application.dtos.TypeHubInfoDto;
import on.logistics.deliveryservice.application.dtos.TypeSpokeInfoDto;
import on.logistics.deliveryservice.application.dtos.request.CreateDeliveryRequestDto;
import on.logistics.deliveryservice.application.dtos.request.SearchDeliveryRequestDto;
import on.logistics.deliveryservice.application.dtos.request.UpdateAssignManagerRequestDto;
import on.logistics.deliveryservice.application.dtos.request.UpdateDeliveryRequestDto;
import on.logistics.deliveryservice.domain.Delivery;
import on.logistics.deliveryservice.domain.dto.CreateDeliveryDto;
import on.logistics.deliveryservice.domain.repository.DeliveryRepository;
import on.logistics.deliveryservice.exception.DeliveryException;
import on.logistics.deliveryservice.exception.DeliveryExceptionCode;
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
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Override
    @Transactional
    public CreateDeliveryResponse createDelivery(CreateDeliveryRequestDto requestDto) {
        DeliveryHubInfoDto hubInfo = deliveryHubInfo(requestDto.description());
        DeliveryUserInfoDto userInfo = deliveryUserInfo();
        CreateDeliveryDto entityRequestDto = CreateDeliveryDto.from(requestDto, hubInfo, userInfo);
        Delivery saved = Delivery.create(entityRequestDto);
        deliveryRepository.save(saved);
        return CreateDeliveryResponse.of(saved.getId());
    }

    @Override
    public PageDto<SearchDeliveryResponse> searchDelivery(SearchDeliveryRequestDto requestDto) {
        Page<Delivery> deliveryPage = deliveryRepository.searchDelivery(requestDto);
        Page<SearchDeliveryResponse> responsePage = deliveryPage.map(SearchDeliveryResponse::from);
        return PageDto.from(responsePage);
    }

    @Override
    public GetDeliveryResponse getDelivery(UUID id) {
        Delivery delivery = getOrElseThrow(id);
        return GetDeliveryResponse.from(delivery);
    }

    @Override
    @Transactional
    public UpdateDeliveryResponse updateDelivery(UpdateDeliveryRequestDto requestDto) {
        DeliveryHubInfoDto hubInfo = deliveryHubInfo(requestDto.description());
        Delivery delivery = getOrElseThrow(requestDto.deliveryId());
        delivery.update(requestDto.description(), hubInfo);
        return UpdateDeliveryResponse.of(delivery.getId());
    }

    @Override
    @Transactional
    public void deleteDelivery(UUID id) {
        Delivery delivery = getOrElseThrow(id);
        deliveryRepository.delete(delivery);
    }

    @Override
    @Transactional
    public UpdateAssignManagerResponse updateAssignManager(
        UpdateAssignManagerRequestDto updateAssignManagerRequestDto) {
        Delivery delivery = getOrElseThrow(updateAssignManagerRequestDto.deliveryId());
        delivery.updateAssignManager(updateAssignManagerRequestDto.companyDeliveryManagerId());
        return UpdateAssignManagerResponse.of(delivery.getId());
    }

    @Override
    @Transactional
    public UpdateDeliveryStatusHubMovingResponse updateDeliveryStatusHubMoving(UUID id) {
        Delivery delivery = getOrElseThrow(id);
        delivery.updateDeliveryStatusHubMoving();
        return UpdateDeliveryStatusHubMovingResponse.of(delivery.getId());
    }

    @Override
    @Transactional
    public UpdateDeliveryStatusHubArriveResponse updateDeliveryStatusHubArrive(UUID id) {
        Delivery delivery = getOrElseThrow(id);
        delivery.updateDeliveryStatusHubArrive();
        return UpdateDeliveryStatusHubArriveResponse.of(delivery.getId());
    }

    @Override
    @Transactional
    public UpdateDeliveryStatusCompanyMovingResponse updateDeliveryStatusCompanyMoving(UUID id) {
        Delivery delivery = getOrElseThrow(id);
        delivery.updateDeliveryStatusCompanyMoving();
        return UpdateDeliveryStatusCompanyMovingResponse.of(delivery.getId());
    }

    @Override
    @Transactional
    public UpdateDeliveryStatusCompanyArriveResponse updateDeliveryStatusCompanyArrive(UUID id) {
        Delivery delivery = getOrElseThrow(id);
        delivery.updateDeliveryStatusCompanyArrive();
        return UpdateDeliveryStatusCompanyArriveResponse.of(delivery.getId());
    }

    @Override
    @Transactional
    public UpdateDeliveryStatusCancelResponse updateDeliveryStatusCancel(UUID id) {
        Delivery delivery = getOrElseThrow(id);
        delivery.updateDeliveryStatusCancel();
        return UpdateDeliveryStatusCancelResponse.of(delivery.getId());
    }

    public DeliveryHubInfoDto deliveryHubInfo(String description) {
        // todo: 목적지로 map 호출해서 목적지 위도, 경도 받아오기
        typeHubInfo();
        // todo: 목적지 위도, 경도로 중앙 허브 세 개 중 어디가 가까운지 찾기
        typeSpokeInfo();
        // todo: 중앙 허브 근처에 관리되고 있는 곳에서도 어디가 제일 가까운지 찾아서 목적지 허브에 넣기
        UUID endHubId = UUID.randomUUID();
        return DeliveryHubInfoDto.of(endHubId);
    }

    public List<TypeHubInfoDto> typeHubInfo() {
        // todo: 허브에게서 중앙 허브들 정보를 받는다.
        return null;
    }

    public List<TypeSpokeInfoDto> typeSpokeInfo() {
        // todo: 목적지에서 가장 가까운 중앙 허브를 기준으로 연결된 허브 정보들을 받는다.
        return null;
    }

    public DeliveryUserInfoDto deliveryUserInfo() {
        // todo: 요청이 들어온 패스포트에서 유저 이름 및 정보 확인
        String recipient = "임시";
        String recipientSlackEmail = "user@slack.com";
        return DeliveryUserInfoDto.of(recipient, recipientSlackEmail);
    }

    private Delivery getOrElseThrow(UUID deliveryId) {
        return deliveryRepository.findById(deliveryId)
            .orElseThrow(() -> new DeliveryException(DeliveryExceptionCode.DELIVERY_NOT_FOUND));
    }
}
