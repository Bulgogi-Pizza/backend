package on.logistics.hubservice.infrastructure.clients.deliverymanager;

import feign.Response;
import lombok.RequiredArgsConstructor;
import on.logistics.hubservice.application.dtos.request.AssignDeliveryManagerRequestDto;
import on.logistics.hubservice.global.util.FeignClientResponseUtils;
import on.logistics.hubservice.infrastructure.clients.deliverymanager.feign.DeliveryManagerServiceFeignClient;
import on.logistics.hubservice.infrastructure.clients.deliverymanager.feign.dtos.request.AssignDeliveryManagerRequest;
import on.logistics.hubservice.infrastructure.clients.deliverymanager.feign.dtos.response.AssignDeliveryManagerResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryManagerServiceClientImpl implements DeliveryManagerServiceClient {

    private final DeliveryManagerServiceFeignClient deliveryManagerServiceFeignClient;

    @Override
    public AssignDeliveryManagerResponse assignDeliveryManager(
        AssignDeliveryManagerRequestDto requestDto) {
        final var request = AssignDeliveryManagerRequest.of(requestDto);
        Response response = deliveryManagerServiceFeignClient.assignDeliveryManager(request);
        return FeignClientResponseUtils.getBody(response, AssignDeliveryManagerResponse.class);
    }
}
