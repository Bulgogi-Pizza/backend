package on.logistics.orderservice.application.service;

import on.logistics.orderservice.infrastructure.clients.slack.dtos.SendMessageRequestDto;

public interface SlackService {

    void sendMessageTo(SendMessageRequestDto request);
}
