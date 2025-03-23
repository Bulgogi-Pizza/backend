package on.logistics.deliveryservice.application.dtos.request;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public record SearchDeliveryRecordRequestDto(UUID deliveryId, UUID startHubId, UUID endHubId,
                                             UUID userId, Pageable pageable,
                                             HttpServletRequest httpServletRequest) {

    public static SearchDeliveryRecordRequestDto from(UUID deliveryId, UUID startHubId,
        UUID endHubId, UUID userId, Pageable pageable, HttpServletRequest httpServletRequest) {
        return new SearchDeliveryRecordRequestDto(deliveryId, startHubId, endHubId,
            userId, pageable, httpServletRequest);
    }

}
