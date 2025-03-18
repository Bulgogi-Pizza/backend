package on.logistics.deliveryservice.application.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.deliveryservice.application.dtos.request.CreateDeliveryHubInfoDto;
import on.logistics.deliveryservice.application.dtos.request.CreateDeliveryRequestDto;
import on.logistics.deliveryservice.application.dtos.request.CreateDeliveryUserInfoDto;
import on.logistics.deliveryservice.domain.Delivery;
import on.logistics.deliveryservice.domain.dto.CreateDeliveryDto;
import on.logistics.deliveryservice.domain.repository.DeliveryRepository;
import on.logistics.deliveryservice.presentation.dtos.response.CreateDeliveryResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Override
    public CreateDeliveryResponse createDelivery(CreateDeliveryRequestDto requestDto) {
        CreateDeliveryHubInfoDto hubInfo = createDeliveryHubInfo(requestDto);
        CreateDeliveryUserInfoDto userInfo = createDeliveryUserInfo();
        CreateDeliveryDto entityRequestDto = CreateDeliveryDto.from(requestDto, hubInfo, userInfo);
        Delivery.create(entityRequestDto);
        Delivery saved = Delivery.create(entityRequestDto);
        return CreateDeliveryResponse.of(saved.getId());
    }

    public CreateDeliveryHubInfoDto createDeliveryHubInfo(CreateDeliveryRequestDto requestDto) {
        // todo: 목적지로 map 호출해서 목적지 위도, 경도 받아오기
        // todo: 목적지 위도, 경도로 중앙 허브 세 개 중 어디가 가까운지 찾기
        // todo: 중앙 허브 근처에 관리되고 있는 곳에서도 어디가 제일 가까운지 찾아서 목적지 허브에 넣기
        UUID endHubId = UUID.randomUUID();
        return CreateDeliveryHubInfoDto.of(endHubId);
    }

    public CreateDeliveryUserInfoDto createDeliveryUserInfo() {
        // todo: 요청이 들어온 패스포트에서 유저 이름 및 정보 확인
        String recipient = "임시";
        String recipientSlackEmail = "user@slack.com";
        return CreateDeliveryUserInfoDto.of(recipient, recipientSlackEmail);
    }
}
