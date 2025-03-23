package on.logistics.slackservice.application;

import java.util.UUID;
import on.logistics.slackservice.application.dtos.SlackMessageRequestDto;
import on.logistics.slackservice.application.dtos.UpdateSlackMessageRequestDto;
import on.logistics.slackservice.presentation.dtos.ReadSlackMessageResponse;
import on.logistics.slackservice.presentation.dtos.SlackMessageResponse;
import on.logistics.slackservice.presentation.dtos.UpdateSlackMessageResponse;

public interface SlackMessageService {

    SlackMessageResponse sendSlackMessage(SlackMessageRequestDto slackMessageRequestDto);

    ReadSlackMessageResponse getSlackMessage(UUID id);

    UpdateSlackMessageResponse updateSlackMessage(UpdateSlackMessageRequestDto requestDto);

    void deleteMessage(UUID id);
}
