package on.logistics.deliveryservice.application.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.deliveryservice.application.dtos.DeliveryHubInfoDto;
import on.logistics.deliveryservice.application.dtos.DeliveryUserInfoDto;
import on.logistics.deliveryservice.application.dtos.request.CreateDeliveryRequestDto;
import on.logistics.deliveryservice.application.dtos.request.UpdateDeliveryRequestDto;
import on.logistics.deliveryservice.domain.Delivery;
import on.logistics.deliveryservice.domain.dto.CreateDeliveryDto;
import on.logistics.deliveryservice.domain.repository.DeliveryRepository;
import on.logistics.deliveryservice.exception.DeliveryException;
import on.logistics.deliveryservice.exception.DeliveryExceptionCode;
import on.logistics.deliveryservice.presentation.dtos.response.CreateDeliveryResponse;
import on.logistics.deliveryservice.presentation.dtos.response.UpdateDeliveryResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Override
    public CreateDeliveryResponse createDelivery(CreateDeliveryRequestDto requestDto) {
        DeliveryHubInfoDto hubInfo = deliveryHubInfo(requestDto.description());
        DeliveryUserInfoDto userInfo = deliveryUserInfo();
        CreateDeliveryDto entityRequestDto = CreateDeliveryDto.from(requestDto, hubInfo, userInfo);
        Delivery.create(entityRequestDto);
        Delivery saved = Delivery.create(entityRequestDto);
        return CreateDeliveryResponse.of(saved.getId());
    }

    @Override
    @Transactional
    public UpdateDeliveryResponse updateDelivery(UpdateDeliveryRequestDto requestDto) {
        DeliveryHubInfoDto hubInfo = deliveryHubInfo(requestDto.description());
        Delivery delivery = deliveryRepository.findById(requestDto.deliveryId())
            .orElseThrow(() -> new DeliveryException(
                DeliveryExceptionCode.DELIVERY_NOT_FOUND));
        delivery.update(requestDto.description(), hubInfo);
        return UpdateDeliveryResponse.of(delivery.getId());
    }

    public DeliveryHubInfoDto deliveryHubInfo(String description) {
        // todo: 목적지로 map 호출해서 목적지 위도, 경도 받아오기
        // todo: 목적지 위도, 경도로 중앙 허브 세 개 중 어디가 가까운지 찾기
        // todo: 중앙 허브 근처에 관리되고 있는 곳에서도 어디가 제일 가까운지 찾아서 목적지 허브에 넣기
        UUID endHubId = UUID.randomUUID();
        return DeliveryHubInfoDto.of(endHubId);
    }

    public DeliveryUserInfoDto deliveryUserInfo() {
        // todo: 요청이 들어온 패스포트에서 유저 이름 및 정보 확인
        String recipient = "임시";
        String recipientSlackEmail = "user@slack.com";
        return DeliveryUserInfoDto.of(recipient, recipientSlackEmail);
    }
}
