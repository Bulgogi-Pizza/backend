package on.logistics.aiservice.infrastructure.clients.webclient.gpt.dtos;

public record ChatRequestDto(
    String prompt
) {

    public static ChatRequestDto from(String requestPrompt) {
        return new ChatRequestDto(requestPrompt);
    }
}
