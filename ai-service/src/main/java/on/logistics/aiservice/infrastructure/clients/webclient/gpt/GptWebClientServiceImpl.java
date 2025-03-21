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
            .bodyValue(GptAIDto.from(MODEL, prompt))
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
        String pattern = "(\\d{4}년 \\d{1,2}월 \\d{1,2}일 \\d{1,2}시)";
        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile(pattern).matcher(text);

        if (matcher.find()) {
            String dateText = matcher.group();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시");
            return LocalDateTime.parse(dateText, formatter);
        } else {
            throw new DateTimeParseException("날짜 및 시간 형식이 올바르지 않습니다.", text, 0);
        }
    }
}