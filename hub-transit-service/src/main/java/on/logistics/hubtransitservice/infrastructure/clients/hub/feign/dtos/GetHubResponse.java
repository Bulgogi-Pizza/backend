package on.logistics.hubtransitservice.infrastructure.clients.hub.feign.dtos;

import java.util.UUID;

public record GetHubResponse(
    UUID id,
    String name,
    String type,
    String address,
    String latitude
) {

}
