package on.logistics.orderservice.infrastructure.clients.ai;

import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.orderservice.application.service.AIService;
import on.logistics.orderservice.global.utils.FeignClientResponseUtils;
import on.logistics.orderservice.infrastructure.clients.ai.dtos.GenerateShippingDeadlineRequestDto;
import on.logistics.orderservice.infrastructure.clients.ai.feign.AIServiceFeignClient;
import on.logistics.orderservice.infrastructure.clients.ai.feign.dtos.GenerateShippingDeadlineRequest;
import on.logistics.orderservice.infrastructure.clients.ai.feign.dtos.GenerateShippingDeadlineResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AIServiceImpl implements AIService {

    private final AIServiceFeignClient aiServiceFeignClient;

    @Override
    public GenerateShippingDeadlineResponse generateShippingDeadline(
        GenerateShippingDeadlineRequestDto requestDto
    ) {
        log.info("배송 시한 생성 요청");
        GenerateShippingDeadlineRequest request = GenerateShippingDeadlineRequest.from(requestDto);
        Response response = aiServiceFeignClient.generateShippingDeadline(request);
        return FeignClientResponseUtils.getBody(response, GenerateShippingDeadlineResponse.class);
    }
}
