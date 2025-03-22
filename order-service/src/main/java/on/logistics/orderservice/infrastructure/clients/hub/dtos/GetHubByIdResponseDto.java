package on.logistics.orderservice.infrastructure.clients.hub.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record GetHubByIdResponseDto(
    UUID id,
    String hubName,
    String hubType,
    String address,
    BigDecimal latitude,
    BigDecimal longitude
) {

}
