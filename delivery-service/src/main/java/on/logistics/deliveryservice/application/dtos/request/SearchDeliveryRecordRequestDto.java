package on.logistics.deliveryservice.application.dtos.request;

import java.util.UUID;
import org.springframework.data.domain.Pageable;

public record SearchDeliveryRecordRequestDto(UUID deliveryId, UUID startHubId, UUID endHubId,
                                             UUID deliveryManagerId, Pageable pageable) {

    public static SearchDeliveryRecordRequestDto from(UUID deliveryId, UUID startHubId,
        UUID endHubId, UUID deliveryManagerId, Pageable pageable) {
        return new SearchDeliveryRecordRequestDto(deliveryId, startHubId, endHubId,
            deliveryManagerId, pageable);
    }

}
