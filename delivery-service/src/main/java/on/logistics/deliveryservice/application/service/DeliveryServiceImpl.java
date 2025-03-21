package on.logistics.deliveryservice.application.service;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.deliveryservice.application.dtos.DeliveryHubInfoDto;
import on.logistics.deliveryservice.application.dtos.DeliveryUserInfoDto;
import on.logistics.deliveryservice.application.dtos.request.CreateDeliveryRequestDto;
import on.logistics.deliveryservice.application.dtos.request.SearchDeliveryRequestDto;
import on.logistics.deliveryservice.application.dtos.request.UpdateAssignManagerRequestDto;
import on.logistics.deliveryservice.application.dtos.request.UpdateDeliveryRequestDto;
import on.logistics.deliveryservice.domain.dtos.CreateDeliveryDto;
import on.logistics.deliveryservice.domain.entity.Delivery;
import on.logistics.deliveryservice.domain.repository.DeliveryRepository;
import on.logistics.deliveryservice.exception.DeliveryException;
import on.logistics.deliveryservice.exception.DeliveryExceptionCode;
import on.logistics.deliveryservice.global.application.dtos.PageDto;
import on.logistics.deliveryservice.infrastructure.clients.hub.HubServiceClient;
import on.logistics.deliveryservice.infrastructure.clients.hub.feign.dtos.GetHubInfo;
import on.logistics.deliveryservice.infrastructure.clients.hub.feign.dtos.GetMiddleHubPageInfo;
import on.logistics.deliveryservice.infrastructure.clients.hub.feign.dtos.GetSpokeHubInfo;
import on.logistics.deliveryservice.infrastructure.clients.hub.feign.dtos.HubInfo;
import on.logistics.deliveryservice.infrastructure.clients.hub.feign.dtos.HubType;
import on.logistics.deliveryservice.infrastructure.clients.hubTransit.HubTransitServiceClient;
import on.logistics.deliveryservice.infrastructure.clients.hubTransit.feign.dtos.CreateHubTransitRouteRequest;
import on.logistics.deliveryservice.infrastructure.clients.map.MapServiceClient;
import on.logistics.deliveryservice.infrastructure.clients.map.feign.dtos.GetDestinationInfo;
import on.logistics.deliveryservice.infrastructure.clients.map.feign.dtos.GetHubRouteInfo;
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

@Slf4j(topic = "DeliveryServiceImpl")
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final MapServiceClient mapServiceClient;
    private final HubServiceClient hubServiceClient;
    private final HubTransitServiceClient hubTransitServiceClient;

    @Override
    @Transactional
    public CreateDeliveryResponse createDelivery(CreateDeliveryRequestDto requestDto) {
        GetHubInfo startHubInfo = hubServiceClient.getHubInfo(requestDto.startHubId());
        if (startHubInfo == null) {
            throw new DeliveryException(DeliveryExceptionCode.DELIVERY_START_HUB_NOT_FOUND);
        }
        DeliveryHubInfoDto hubInfo = deliveryHubInfo(requestDto.destination());
        DeliveryUserInfoDto userInfo = deliveryUserInfo();
        CreateDeliveryDto entityRequestDto = CreateDeliveryDto.from(requestDto, hubInfo, userInfo);
        Delivery saved = Delivery.create(entityRequestDto);
        deliveryRepository.save(saved);
        // todo : 비동기 고민
        createHubTransitRouteRequest(requestDto, hubInfo, saved);
        return CreateDeliveryResponse.of(saved.getId());
    }

    private void createHubTransitRouteRequest(CreateDeliveryRequestDto requestDto,
        DeliveryHubInfoDto hubInfo,
        Delivery saved) {
        CreateHubTransitRouteRequest createHubTransitRouteRequest = CreateHubTransitRouteRequest.of(
            requestDto.startHubId(), hubInfo.endHubId(), saved.getId());
        hubTransitServiceClient.createHubTransitRoute(createHubTransitRouteRequest);
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
        DeliveryHubInfoDto hubInfo = deliveryHubInfo(requestDto.destination());
        Delivery delivery = getOrElseThrow(requestDto.deliveryId());
        delivery.update(requestDto.destination(), hubInfo);
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

    public DeliveryHubInfoDto deliveryHubInfo(String destination) {
        // todo: 목적지 위도, 경도 받아옴.
        GetDestinationInfo geocode = mapServiceClient.getGeocode(destination);
        String start = "" + geocode.longitude() + "" + "," + geocode.latitude();
        GetMiddleHubPageInfo getMiddleHubPageInfo = typeHubInfoList();
        GetHubRouteInfo middleRoute = middleRouteInfo(start, getMiddleHubPageInfo);
        UUID middleRouteHubId = middleRouteHubId(getMiddleHubPageInfo, middleRoute);
        GetSpokeHubInfo getSpokeHubInfo = typeSpokeInfoList(middleRouteHubId);
        GetHubRouteInfo endRoute = endRouteInfo(start, getSpokeHubInfo);
        UUID endHubId = endRouteHubId(getSpokeHubInfo, endRoute);

        return DeliveryHubInfoDto.of(endHubId);
    }


    private UUID endRouteHubId(GetSpokeHubInfo getSpokeHubInfo, GetHubRouteInfo endRoute) {
        List<HubInfo> hubs = getSpokeHubInfo.data();
        String spiltEndRouteHubLongitude = String.valueOf(
            endRoute.summary().end().location().get(0)).substring(0, 6);
        String spiltEndRouteHubLatitude = String.valueOf(
            endRoute.summary().end().location().get(1)).substring(0, 6);
        String endHubId = "";
        for (HubInfo typeHubInfo : hubs) {
            String splitHubLongitude = typeHubInfo.longitude().substring(0, 6);
            String splitHubLatitude = typeHubInfo.latitude().substring(0, 6);
            if (splitHubLongitude.equals(spiltEndRouteHubLongitude) && splitHubLatitude
                .equals(spiltEndRouteHubLatitude)) {
                endHubId = typeHubInfo.id();
                break;
            }
        }
        return UUID.fromString(endHubId);
    }

    private GetHubRouteInfo endRouteInfo(String start, GetSpokeHubInfo getSpokeHubInfo) {
        List<HubInfo> hubs = getSpokeHubInfo.data();
        String end = getMapApiEndSerchingString(hubs);
        return mapServiceClient.getRoute(start, end);
    }

    public GetHubRouteInfo middleRouteInfo(String start,
        GetMiddleHubPageInfo getMiddleHubPageInfo) {
        List<HubInfo> hubs = getMiddleHubPageInfo.content();
        String end = getMapApiEndSerchingString(hubs);
        return mapServiceClient.getRoute(start, end);
    }

    public UUID middleRouteHubId(GetMiddleHubPageInfo getMiddleHubPageInfo,
        GetHubRouteInfo middleRoute) {
        List<HubInfo> hubs = getMiddleHubPageInfo.content();
        String spiltMiddleRouteHubLongitude = String.valueOf(
            middleRoute.summary().end().location().get(0)).substring(0, 6);
        String spiltMiddleRouteHubLatitude = String.valueOf(
            middleRoute.summary().end().location().get(1)).substring(0, 6);
        String middleRouteId = "";
        for (HubInfo typeHubInfo : hubs) {
            String splitHubLongitude = typeHubInfo.longitude().substring(0, 6);
            String splitHubLatitude = typeHubInfo.latitude().substring(0, 6);
            if (splitHubLongitude.equals(spiltMiddleRouteHubLongitude) && splitHubLatitude
                .equals(spiltMiddleRouteHubLatitude)) {
                middleRouteId = typeHubInfo.id();
                break;
            }
        }
        return UUID.fromString(middleRouteId);
    }

    public GetMiddleHubPageInfo typeHubInfoList() {
        return hubServiceClient.searchHubs(HubType.HUB);
    }

    public GetSpokeHubInfo typeSpokeInfoList(UUID middleHubId) {
        return hubServiceClient.getSpokeHubInfo(middleHubId);
    }

    public DeliveryUserInfoDto deliveryUserInfo() {
        // todo: 요청이 들어온 패스포트에서 유저 이름 및 정보 확인
        String recipient = "임시";
        String recipientSlackEmail = "user@slack.com";
        return DeliveryUserInfoDto.of(recipient, recipientSlackEmail);
    }

    private String getMapApiEndSerchingString(List<HubInfo> hubs) {
        String end = "";
        for (HubInfo typeHubInfo : hubs) {
            end += ("" + typeHubInfo.longitude() + "," + typeHubInfo.latitude() + ":");
        }
        if (end.endsWith(":")) {
            end = end.substring(0, end.length() - 1);
        }
        return end;
    }

    public Delivery getOrElseThrow(UUID deliveryId) {
        return deliveryRepository.findById(deliveryId)
            .orElseThrow(() -> new DeliveryException(DeliveryExceptionCode.DELIVERY_NOT_FOUND));
    }
}
