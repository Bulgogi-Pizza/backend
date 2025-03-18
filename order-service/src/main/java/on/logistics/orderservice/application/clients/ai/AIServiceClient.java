package on.logistics.orderservice.application.clients.ai;

import on.logistics.orderservice.application.clients.ai.dtos.GenerateShippingDeadlineRequestDto;
import on.logistics.orderservice.application.clients.ai.feign.dtos.GenerateShippingDeadlineResponse;

public interface AIServiceClient {

  GenerateShippingDeadlineResponse generateShippingDeadline(
      GenerateShippingDeadlineRequestDto requestDto);
}
