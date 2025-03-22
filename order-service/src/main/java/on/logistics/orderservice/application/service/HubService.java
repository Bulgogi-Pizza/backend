package on.logistics.orderservice.application.service;

import java.util.UUID;
import on.logistics.orderservice.infrastructure.clients.hub.dtos.GetHubByIdResponseDto;

public interface HubService {

    GetHubByIdResponseDto getHubById(UUID userId);
}
