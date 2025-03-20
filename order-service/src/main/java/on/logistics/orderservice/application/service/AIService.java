package on.logistics.orderservice.application.service;

import on.logistics.orderservice.infrastructure.clients.ai.dtos.GenerateShippingDeadlineRequestDto;
import on.logistics.orderservice.infrastructure.clients.ai.feign.dtos.GenerateShippingDeadlineResponse;

public interface AIService {

  GenerateShippingDeadlineResponse generateShippingDeadline(
      GenerateShippingDeadlineRequestDto requestDto);
}
