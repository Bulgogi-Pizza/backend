package on.logistics.orderservice.infrastructure.clients.slack;

import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.orderservice.application.service.SlackService;
import on.logistics.orderservice.global.utils.FeignClientResponseUtils;
import on.logistics.orderservice.infrastructure.clients.slack.dtos.SendMessageRequestDto;
import on.logistics.orderservice.infrastructure.clients.slack.feign.SlackServiceFeignClient;
import on.logistics.orderservice.infrastructure.clients.slack.feign.dtos.SendMessageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SlackServiceImpl implements SlackService {

    private final SlackServiceFeignClient slackServiceFeignClient;

    @Override
    public void sendMessageTo(SendMessageRequestDto requestDto) {
        log.info("Sending message to slack: {}", requestDto);
        SendMessageRequest request = SendMessageRequest.from(requestDto);
        Response response = slackServiceFeignClient.sendMessage(request);
        FeignClientResponseUtils.validateResponseStatus(response);
    }

}
