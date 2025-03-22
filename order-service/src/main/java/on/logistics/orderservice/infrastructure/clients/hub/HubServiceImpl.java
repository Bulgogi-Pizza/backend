package on.logistics.orderservice.infrastructure.clients.hub;

import feign.Response;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.orderservice.application.service.HubService;
import on.logistics.orderservice.global.utils.FeignClientResponseUtils;
import on.logistics.orderservice.infrastructure.clients.hub.dtos.GetHubByIdResponseDto;
import on.logistics.orderservice.infrastructure.clients.hub.dtos.ValidateHubManagerResponseDto;
import on.logistics.orderservice.infrastructure.clients.hub.feign.HubServiceFeignClient;
import on.logistics.orderservice.infrastructure.clients.hub.feign.dtos.ValidateHubManagerRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class HubServiceImpl implements HubService {

    private final HubServiceFeignClient hubServiceFeignClient;

    public GetHubByIdResponseDto getHubById(UUID userId) {
        log.info("Getting hub manager by user id: {}", userId);
        Response response = hubServiceFeignClient.getHubByUserId(userId);
        return FeignClientResponseUtils.getBody(response, GetHubByIdResponseDto.class);
    }

    @Override
    public ValidateHubManagerResponseDto validateHubManager(UUID companyId, UUID hubId) {
        log.info("Validating hub manager: companyId={}, hubId={}", companyId, hubId);
        ValidateHubManagerRequest request = ValidateHubManagerRequest.of(companyId, hubId);
        Response response = hubServiceFeignClient.validateHubManager(request);
        return FeignClientResponseUtils.getBody(response, ValidateHubManagerResponseDto.class);
    }
}
