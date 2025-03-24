package on.logistics.orderservice.application.service;

import java.util.UUID;
import on.logistics.orderservice.infrastructure.clients.hub.dtos.GetHubByIdResponseDto;
import on.logistics.orderservice.infrastructure.clients.hub.dtos.GetHubManagerIdResponse;
import on.logistics.orderservice.infrastructure.clients.hub.dtos.ValidateHubManagerResponseDto;

public interface HubService {

    GetHubByIdResponseDto getHubById(UUID hubId);

    ValidateHubManagerResponseDto validateHubManager(UUID companyId, UUID hubId);

    GetHubManagerIdResponse getHubManagerId(UUID hubId);
}
