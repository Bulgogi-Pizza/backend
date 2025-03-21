package on.logistics.aiservice.application.service;

import on.logistics.aiservice.infrastructure.clients.dtos.ShippingDeadlineResponseDto;

public interface AIApiService {

    ShippingDeadlineResponseDto chat(String prompt);
}
