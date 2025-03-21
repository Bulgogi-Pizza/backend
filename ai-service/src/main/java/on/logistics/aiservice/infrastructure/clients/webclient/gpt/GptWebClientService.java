package on.logistics.aiservice.infrastructure.clients.webclient.gpt;

import on.logistics.aiservice.infrastructure.clients.dtos.ShippingDeadlineResponseDto;

public interface GptWebClientService {

    ShippingDeadlineResponseDto chat(String prompt);
}
