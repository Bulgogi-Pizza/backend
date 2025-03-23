package on.logistics.hubtransitservice.infrastructure.clients.deliverymanager;

import feign.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.hubtransitservice.global.utils.FeignClientResponseUtils;
import on.logistics.hubtransitservice.infrastructure.clients.deliverymanager.feign.DeliveryManagerFeignClient;
import on.logistics.hubtransitservice.infrastructure.clients.deliverymanager.feign.dtos.AssignDeliveryManagerRequest;
import on.logistics.hubtransitservice.infrastructure.clients.deliverymanager.feign.dtos.AssignDeliveryManagerResponse;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryManagerClientImpl implements DeliveryManagerClient {

    private final DeliveryManagerFeignClient deliveryManagerFeignClient;

    @Override
    public AssignDeliveryManagerResponse assignDeliveryManager(
        AssignDeliveryManagerRequest request,
        HttpServletRequest servletRequest
    ) {
        log.info("배송 담당자 지정 요청, deliveryId: {}", request.deliveryId());
        Response response = deliveryManagerFeignClient.assignDeliveryManager(
            request,
            servletRequest
        );
        log.info("배송 담당자 지정 성공");
        return FeignClientResponseUtils.getBody(response, AssignDeliveryManagerResponse.class);
    }
}