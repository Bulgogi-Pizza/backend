package on.logistics.hubtransitservice.infrastructure.clients.deliverymanager;

import feign.Response;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.hubtransitservice.global.utils.FeignClientResponseUtils;
import on.logistics.hubtransitservice.infrastructure.clients.deliverymanager.feign.DeliveryManagerServiceFeignClient;
import on.logistics.hubtransitservice.infrastructure.clients.deliverymanager.feign.dtos.AssignDeliveryManagerRequest;
import on.logistics.hubtransitservice.infrastructure.clients.deliverymanager.feign.dtos.AssignDeliveryManagerResponse;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryManagerServiceClientImpl implements DeliveryManagerServiceClient {

    private final DeliveryManagerServiceFeignClient deliveryManagerServiceFeignClient;

    @Override
    public UUID assignDeliveryManager(UUID deliveryId, UUID nextHubId, String nextDestinationType) {
        log.info("배송 담당자 할당 요청, deliveryId: {}, endHubId: {}, nextDestinationType: {}", deliveryId,
            nextHubId, nextDestinationType);
        AssignDeliveryManagerRequest request = new AssignDeliveryManagerRequest(deliveryId,
            nextHubId, nextDestinationType);
        Response response = deliveryManagerServiceFeignClient.assignDeliveryManager(request);
        AssignDeliveryManagerResponse assignResponse = FeignClientResponseUtils.getBody(response,
            AssignDeliveryManagerResponse.class);
        return assignResponse.deliveryManagerId();
    }

}
