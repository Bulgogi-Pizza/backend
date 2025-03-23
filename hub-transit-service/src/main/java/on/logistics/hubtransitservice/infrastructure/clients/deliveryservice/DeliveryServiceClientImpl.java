package on.logistics.hubtransitservice.infrastructure.clients.deliveryservice;

import feign.Response;
import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.hubtransitservice.global.utils.FeignClientResponseUtils;
import on.logistics.hubtransitservice.infrastructure.clients.deliveryservice.feign.DeliveryServiceFeignClient;
import on.logistics.hubtransitservice.infrastructure.clients.deliveryservice.feign.dtos.CreateDeliveryRecordRequest;
import on.logistics.hubtransitservice.infrastructure.clients.deliveryservice.feign.dtos.CreateDeliveryRecordResponse;
import on.logistics.hubtransitservice.infrastructure.clients.deliveryservice.feign.dtos.UpdateDeliveryStatusRequest;
import on.logistics.hubtransitservice.infrastructure.clients.deliveryservice.feign.dtos.UpdateDeliveryStatusResponse;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryServiceClientImpl implements DeliveryServiceClient {

    private final DeliveryServiceFeignClient deliveryServiceFeignClient;

    @Override
    public CreateDeliveryRecordResponse createDeliveryRecord(
        CreateDeliveryRecordRequest request,
        HttpServletRequest httpServletRequest
    ) {
        log.info("배송 기록 생성 요청 전송");
        Response response = deliveryServiceFeignClient.createDeliveryRecord(
            request,
            httpServletRequest
        );
        log.info("배송 기록 생성 성공");
        return FeignClientResponseUtils.getBody(response, CreateDeliveryRecordResponse.class);
    }

    @Override
    public UpdateDeliveryStatusResponse updateDeliveryRecordStatus(
        UUID id, UpdateDeliveryStatusRequest request,
        HttpServletRequest httpServletRequest
    ) {
        log.info("배송 기록 상태 수정 요청 전송");
        Response response = deliveryServiceFeignClient.updateDeliveryRecordStatus(
            id, request, httpServletRequest
        );
        log.info("배송 기록 상태 수정 성공");
        return FeignClientResponseUtils.getBody(response, UpdateDeliveryStatusResponse.class);
    }
}
