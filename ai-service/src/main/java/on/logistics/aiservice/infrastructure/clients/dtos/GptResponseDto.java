package on.logistics.aiservice.infrastructure.clients.dtos;

import java.util.List;

public record GptResponseDto(
    String id,
    String object,
    long created,
    String model,
    List<Choice> choices
) {

    public record Choice(
        int index,
        Message message,
        Object logprobs,
        String finishReason
    ) {

        public record Message(
            String role,
            String content
        ) {

        }
    }
}
