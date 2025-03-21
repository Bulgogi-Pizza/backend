package on.logistics.aiservice.application.service;

import on.logistics.aiservice.application.service.dtos.GenerateShippingDeadlineRequestDto;
import on.logistics.aiservice.infrastructure.clients.dtos.ShippingDeadlineResponseDto;

public interface AIService {

    ShippingDeadlineResponseDto generateShippingDeadline(
        GenerateShippingDeadlineRequestDto requestDto);
}
