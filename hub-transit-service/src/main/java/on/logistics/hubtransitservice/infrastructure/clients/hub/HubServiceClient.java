package on.logistics.hubtransitservice.infrastructure.clients.hub;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import on.logistics.hubtransitservice.infrastructure.clients.hub.feign.dtos.GetHubResponse;

public interface HubServiceClient {

    GetHubResponse getHubById(UUID hubId, HttpServletRequest httpServletRequest);

    GetHubResponse getHubByName(String hubName, HttpServletRequest httpServletRequest);
}
