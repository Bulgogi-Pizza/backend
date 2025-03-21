package on.logistics.hubtransitservice.application.dtos.read;

import java.util.UUID;
import org.springframework.data.domain.Pageable;

public record SearchHubTransitRequestDto(
    UUID deliveryId,
    String currentHubName,
    Pageable pageable
) {

    public static SearchHubTransitRequestDto of(UUID deliveryId, String currentHubName,
        Pageable pageable
    ) {
        return new SearchHubTransitRequestDto(deliveryId, currentHubName, pageable);
    }

}
