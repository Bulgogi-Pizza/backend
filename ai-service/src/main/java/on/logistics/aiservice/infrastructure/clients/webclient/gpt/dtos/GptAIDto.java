package on.logistics.aiservice.infrastructure.clients.webclient.gpt.dtos;

import java.util.List;

public record GptAIDto(
    String model,
    List<GptAIMessage> messages
) {

    public static GptAIDto from(String model, String content) {
        return new GptAIDto(
            model,
            List.of(new GptAIMessage("user", content))
        );
    }
}
