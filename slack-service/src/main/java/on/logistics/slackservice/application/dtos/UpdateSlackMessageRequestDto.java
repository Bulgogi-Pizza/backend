package on.logistics.slackservice.application.dtos;

import java.util.UUID;
import lombok.Builder;

@Builder
public record UpdateSlackMessageRequestDto(
    UUID id,
    String message
) {

}
