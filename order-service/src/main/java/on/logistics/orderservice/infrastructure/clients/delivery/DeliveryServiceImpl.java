package on.logistics.orderservice.infrastructure.clients.delivery;

import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.orderservice.application.service.DeliveryService;
import on.logistics.orderservice.infrastructure.clients.delivery.dtos.DeliveryRequestDto;
import on.logistics.orderservice.infrastructure.clients.delivery.feign.DeliveryServiceFeignClient;
import on.logistics.orderservice.infrastructure.clients.delivery.feign.dtos.DeliveryRequest;
import on.logistics.orderservice.global.utils.FeignClientResponseUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

  private final DeliveryServiceFeignClient deliveryServiceFeignClient;

  @Override
  public void deliveryRequest(DeliveryRequestDto requestDto) {
    DeliveryRequest request = DeliveryRequest.from(requestDto);
    Response response = deliveryServiceFeignClient.deliveryRequest(request);
    FeignClientResponseUtils.validateResponseStatus(response);
  }
}
