package on.logistics.aiservice.infrastructure.clients;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.aiservice.application.service.AIApiService;
import on.logistics.aiservice.infrastructure.clients.webclient.gpt.GptWebClientService;
import on.logistics.aiservice.infrastructure.clients.dtos.ShippingDeadlineResponseDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AIApiServiceImpl implements AIApiService {

    private final GptWebClientService gptWebClientService;

    @Override
    public ShippingDeadlineResponseDto chat(String prompt) {
        return gptWebClientService.chat(prompt);
    }
}
