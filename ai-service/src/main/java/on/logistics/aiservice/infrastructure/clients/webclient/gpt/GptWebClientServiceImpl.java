package on.logistics.aiservice.infrastructure.clients.webclient.gpt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.aiservice.exception.AIException;
import on.logistics.aiservice.exception.AIExceptionCode;
import on.logistics.aiservice.infrastructure.clients.dtos.GptResponseDto;
import on.logistics.aiservice.infrastructure.clients.dtos.ShippingDeadlineResponseDto;
import on.logistics.aiservice.infrastructure.clients.webclient.gpt.dtos.GptAIDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class GptWebClientServiceImpl implements GptWebClientService {

    private static final String BASE_URL = "https://api.openai.com/v1";
    private static final String MODEL = "gpt-3.5-turbo";
    private static final int TEMPERATURE = 1;
    private static final int MAX_TOKEN = 12;

    private final WebClient webClient;

    @Value("${openai.api-key}")
    private String apiKey;

    @Override
    public ShippingDeadlineResponseDto chat(String prompt) {
        log.info("chat() 호출: {}", prompt);
        for (int i = 10; i > 0; i--) {
            GptResponseDto response = send(prompt);
            log.info("chat() 응답: {}", response);
            Optional<ShippingDeadlineResponseDto> optionalChatResponseDto =
                tryConvertToLocalDateTime(response.choices().get(0).message().content());
            if (optionalChatResponseDto.isPresent()) {
                return optionalChatResponseDto.get();
            }
        }
        throw new AIException(AIExceptionCode.AI_SERVICE_INTERNAL_SERVER_ERROR);
    }

    private GptResponseDto send(String prompt) {
        return webClient.post()
            .uri(BASE_URL + "/chat/completions")
            .header("Authorization", "Bearer " + apiKey)
            .header("Content-Type", "application/json")
            .bodyValue(GptAIDto.from(MODEL, prompt, TEMPERATURE, MAX_TOKEN))
            .retrieve()
            .bodyToMono(GptResponseDto.class)
            .block();
    }

    private Optional<ShippingDeadlineResponseDto> tryConvertToLocalDateTime(String response) {
        try {
            LocalDateTime shippingDeadline = convertToLocalDateTime(response);
            return Optional.of(ShippingDeadlineResponseDto.from(shippingDeadline));
        } catch (DateTimeParseException e) {
            log.warn("LocalDateTime 타입으로 변환 실패: {}", e.getMessage());
        }
        return Optional.empty();
    }

    private LocalDateTime convertToLocalDateTime(String text) {
        // 텍스트에서 날짜와 시간을 추출하는 정규식 패턴
        return LocalDateTime.parse(text);
    }
}