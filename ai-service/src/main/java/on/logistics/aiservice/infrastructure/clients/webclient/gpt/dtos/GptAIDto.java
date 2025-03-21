package on.logistics.aiservice.infrastructure.clients.webclient.gpt.dtos;

import java.util.List;

public record GptAIDto(
    String model,
    List<GptAIMessage> messages,
    int max_tokens,
    int temperature
) {

    public static GptAIDto from(String model, String content, int temperature, int max_tokens) {
        return new GptAIDto(
            model,
            List.of(new GptAIMessage("user", content)),
            max_tokens,
            temperature
        );
    }
}
