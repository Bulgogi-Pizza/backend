package on.logistics.slackservice.domain.dtos;

import java.util.UUID;
import lombok.Builder;

@Builder
public record CreateSlackMessageDto(
    String slackSendEmail,
    String slackReceiveEmail,
    UUID userSendId,
    UUID userReceiveId,
    String message
) {

}
